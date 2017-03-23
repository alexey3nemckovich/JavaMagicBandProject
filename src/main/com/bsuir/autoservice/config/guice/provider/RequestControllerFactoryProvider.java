package main.com.bsuir.autoservice.config.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.IControllerFactory;
import main.com.bsuir.autoservice.controller.factory.exception.ControllerFactoryException;
import main.com.bsuir.autoservice.controller.impl.BeanMainController;
import main.com.bsuir.autoservice.controller.impl.BeanTableController;
import main.com.bsuir.autoservice.controller.impl.UserController;
import main.com.bsuir.autoservice.library.RequestType;

import java.util.HashMap;
import java.util.Map;

public class RequestControllerFactoryProvider implements Provider<Map<RequestType,IControllerFactory>> {
    private final Map<RequestType,IControllerFactory> requestControllerFactory;
    private static final int supportedRequestTypeCount = 2;

    @Inject
    public RequestControllerFactoryProvider(Injector injector, RequestType[] supportedRequestType,
                                            @Named("defaultControllerFactory") IControllerFactory defaultControllerFactory){
        if (supportedRequestType.length != supportedRequestTypeCount)
            throw new RuntimeException("Count requestType not equals");
        requestControllerFactory = createRequestFactory(supportedRequestType,defaultControllerFactory);
        registerAllBindings(injector);
    }

    @Override
    public Map<RequestType, IControllerFactory> get() {
        return requestControllerFactory;
    }

    private static Map<RequestType, IControllerFactory> createRequestFactory(RequestType[] supportedRequestType,
                                                                             IControllerFactory defaultControllerFactory) {
        Map<RequestType, IControllerFactory> map = new HashMap<>();
        for (RequestType requestType : supportedRequestType)
            map.put(requestType, defaultControllerFactory);
        return map;
    }

    private void addRequestBind(RequestType requestType, String url, IController controller) throws ControllerFactoryException {
        addMapBind(requestControllerFactory.get(requestType),url,controller);
    }

    private void addMapBind(IControllerFactory commandFactory, String url, IController controller) throws ControllerFactoryException {
        commandFactory.addController(url, controller);
    }

    private void registerAllBindings(Injector controllerFactoryInjector) {
        try {
            addGetBind(controllerFactoryInjector);
            addPostBind(controllerFactoryInjector);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void addPostBind(Injector controllerFactoryInjector)  throws ControllerFactoryException {
    }

    private void addGetBind(Injector controllerFactoryInjector) throws ControllerFactoryException {
        addRequestBind(RequestType.GET, "/bean/user", controllerFactoryInjector.getInstance(UserController.class));
        addRequestBind(RequestType.GET, "/bean/main", controllerFactoryInjector.getInstance(BeanMainController.class));
        addRequestBind(RequestType.GET, "/bean/table", controllerFactoryInjector.getInstance(BeanTableController.class));
    }
}
