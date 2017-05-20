package main.com.bsuir.autoservice.config;

import com.google.inject.Singleton;
import main.com.bsuir.autoservice.config.permission.Permission;
import main.com.bsuir.autoservice.config.permission.PermissionAccessType;
import main.com.bsuir.autoservice.config.permission.PermissionLevel;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.bean.*;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class RouteConfig {
    private final Map<String, Class<? extends IController>> controllerMap;
    private final Map<String, Permission> permissionMap;

    public RouteConfig(){
        controllerMap = new HashMap<>();
        permissionMap = new HashMap<>();
        init();
    }

    private void init() {
        initGetRoute();
        // TODO: write check method type
        initPostRoute();
    }

    private void initGetRoute() {
        // example add permissions
        addControllerClassForUrlAction("/bean", BeanController.class, PermissionLevel.GUEST);
        addControllerClassForUrlAction("/bean/add", BeanAddController.class);
        addControllerClassForUrlAction("/bean/view", BeanViewController.class);
        addControllerClassForUrlAction("/bean/edit", BeanEditController.class);
        addControllerClassForUrlAction("/bean/dependency/add", BeanDependencyAddController.class);
        addControllerClassForUrlAction("/bean/dependency/view", BeanDependencyViewController.class);
        addControllerClassForUrlAction("/bean/dependency/edit", BeanDependencyEditController.class);
    }

    private void initPostRoute() {
        // Not have post request
    }

    private void addControllerClassForUrlAction(String url,
                                                Class<? extends IController> controllerClass
    ) {
        controllerMap.put(url, controllerClass);
    }

    private void addControllerClassForUrlAction(String url,
                                           Class<? extends IController> controllerClass,
                                           PermissionLevel permissionLevel
    ) {
        addControllerClassForUrlAction(url, controllerClass, new Permission.Builder().setNestedPermissionLevel(permissionLevel));
    }

    private void addControllerClassForUrlAction(String url,
                                           Class<? extends IController> controllerClass,
                                           PermissionAccessType permissionAccessType
    ) {
        addControllerClassForUrlAction(url, controllerClass, new Permission.Builder().setNestedAccess(permissionAccessType));
    }

    private void addControllerClassForUrlAction(String url,
                                           Class<? extends IController> controllerClass,
                                           Permission.Builder permissionBuilder
    ) {
        addControllerClassForUrlAction(url, controllerClass);
        permissionMap.put(url, permissionBuilder.build());
    }

    public Map<String, Class<? extends IController>> getControllerMap() {
        return controllerMap;
    }

    public Map<String, Permission> getPermissionMap() {
        return permissionMap;
    }
}
