package main.com.bsuir.autoservice.servlet.filter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import main.com.bsuir.autoservice.binding.annotation.InjectLogger;
import main.com.bsuir.autoservice.binding.annotation.PermissionUrl;
import main.com.bsuir.autoservice.config.permission.Permission;
import main.com.bsuir.autoservice.config.permission.PermissionLevel;
import main.com.bsuir.autoservice.session.ISession;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Singleton
public class PermissionFilter implements Filter {
    private final Map<String, Permission> permissionMap;
    private final Provider<ISession> sessionProvider;
    @InjectLogger
    Logger logger;

    @Inject
    public PermissionFilter(@PermissionUrl Map<String, Permission> permissionMap, Provider<ISession> sessionProvider) {
        this.permissionMap = permissionMap;
        this.sessionProvider = sessionProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String url = getUrl(httpServletRequest.getRequestURI());
        if (!permissionMap.containsKey(url) || isUserHavePermissions(permissionMap.get(url))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private boolean isUserHavePermissions(Permission permission) {
        boolean isHavePermission = false;
        try {
            isHavePermission = isUserHavePermissionProceed(permission, sessionProvider.get().getUserLevel());
        } catch (Exception e){
            logger.error(String.format("Problem with checks permissions, %s", e.getMessage()));
        }
        return isHavePermission;
    }

    private String getUrl(String requestURL) {
        return requestURL.replace(".ass", "");
    }

    private static boolean isUserHavePermissionProceed(final Permission permission, PermissionLevel userLevel) {
        List<PermissionLevel> accessedPermissions = permission.getPermissionLevel();

        if (accessedPermissions.contains(userLevel)) {
            return true;
        }

        boolean havePermission;
        // equally checked early
        switch (permission.getAccess()) {
            case EQUALLY_OR_LOWER:
                PermissionLevel minPermission = Collections.min(accessedPermissions);
                havePermission = minPermission.compareTo(userLevel) > 0;
                break;
            case EQUALLY_OR_HIGHER:
                PermissionLevel maxPermission = Collections.max(accessedPermissions);
                havePermission = maxPermission.compareTo(userLevel) < 0;
                break;
            case EQUALLY:
                // check early
                havePermission = false;
                break;
            default:
                throw new UnsupportedOperationException("Permission accesses aren't realized at all");
        }

        return havePermission;
    }
}
