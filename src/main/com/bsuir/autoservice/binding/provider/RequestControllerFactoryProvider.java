package main.com.bsuir.autoservice.binding.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.binding.annotation.Supported;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.ControllerFactory;
import main.com.bsuir.autoservice.controller.factory.exception.ControllerFactoryException;
import main.com.bsuir.autoservice.controller.bean.BeanMainController;
import main.com.bsuir.autoservice.controller.bean.BeanTableController;
import main.com.bsuir.autoservice.controller.bean.UserController;
import main.com.bsuir.autoservice.library.RequestType;

import java.util.HashMap;
import java.util.Map;

public class RequestControllerFactoryProvider implements Provider<Map<RequestType,ControllerFactory>> {
    private final Map<RequestType,ControllerFactory> controllerFactoryMap;

    @Inject
    public RequestControllerFactoryProvider(Injector injector,
                                            @Supported RequestType[] supportedRequestType,
                                            @Default ControllerFactory defaultControllerFactory){
        controllerFactoryMap = new HashMap<>();
        for (RequestType requestType : supportedRequestType)
            controllerFactoryMap.put(requestType, defaultControllerFactory);
        initControlFactories(injector);
    }

    @Override
    public Map<RequestType, ControllerFactory> get() {
        return controllerFactoryMap;
    }

    private void initControlFactories(Injector injector) {
        try {
            addGetRequestControllers(injector);
            addPostRequestControllers(injector);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void addGetRequestControllers(Injector injector) throws ControllerFactoryException {
        addUrlControllerForRequest(RequestType.GET, "/bean/user", injector.getInstance(UserController.class));
        addUrlControllerForRequest(RequestType.GET, "/bean/main", injector.getInstance(BeanMainController.class));
        addUrlControllerForRequest(RequestType.GET, "/bean/table", injector.getInstance(BeanTableController.class));
    }

    private void addPostRequestControllers(Injector injector)  throws ControllerFactoryException {
    }

    private void addUrlControllerForRequest(RequestType requestType,
                                            String url,
                                            IController controller
    ) throws ControllerFactoryException {
        controllerFactoryMap.get(requestType).
                addController(url, controller);
    }
}
