package main.com.bsuir.autoservice.servlet.filter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import main.com.bsuir.autoservice.infrastructure.listener.DatabaseConnectionListener;

import javax.servlet.*;
import java.io.IOException;

@Singleton
public class DatabaseConnectionFilter implements Filter{

    private final Provider<DatabaseConnectionListener> databaseConnectionListenerProvider;

    @Inject
    public DatabaseConnectionFilter(Provider<DatabaseConnectionListener> databaseConnectionListenerProvider){
        this.databaseConnectionListenerProvider = databaseConnectionListenerProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        try(DatabaseConnectionListener ignored = databaseConnectionListenerProvider.get()
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
