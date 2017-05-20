package main.com.bsuir.autoservice.binding.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.config.RouteConfig;
import main.com.bsuir.autoservice.config.permission.Permission;

public class PermissionProvider extends MapProvider<String,Permission> {

    @Inject
    public PermissionProvider(Injector injector) {
        super(injector);
    }

    @Override
    protected void initMap(Injector injector) {
        map = injector.getInstance(RouteConfig.class).getPermissionMap();
    }
}
