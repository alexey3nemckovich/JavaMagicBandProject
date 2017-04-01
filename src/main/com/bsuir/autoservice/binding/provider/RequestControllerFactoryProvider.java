package main.com.bsuir.autoservice.binding.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.binding.annotation.Supported;
import main.com.bsuir.autoservice.controller.ControllerId;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.controller.bean.BeanMainController;
import main.com.bsuir.autoservice.controller.bean.BeanTableController;
import main.com.bsuir.autoservice.controller.bean.UserController;
import main.com.bsuir.autoservice.library.RequestType;

import java.util.HashMap;
import java.util.Map;

public class RequestControllerFactoryProvider implements Provider<Map<ControllerId, IController>> {

    @Inject
    public RequestControllerFactoryProvider(Injector injector,
                                            @Supported RequestType[] supportedRequestType){
        controllerMap = new HashMap<>();
        initControlMap(injector);
    }

    @Override
    public Map<ControllerId, IController> get() {
        return controllerMap;
    }

    private void initControlMap(Injector injector) {
        try {
            addGetRequestControllers(injector);
            addPostRequestControllers(injector);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void addGetRequestControllers(Injector injector) throws ControllerException {
        addUrlControllerForRequestType(RequestType.GET, "/bean/user", injector.getInstance(UserController.class));
        addUrlControllerForRequestType(RequestType.GET, "/bean/main", injector.getInstance(BeanMainController.class));
        addUrlControllerForRequestType(RequestType.GET, "/bean/table", injector.getInstance(BeanTableController.class));
    }

    private void addPostRequestControllers(Injector injector)
            throws ControllerException {

    }

    private void addUrlControllerForRequestType(RequestType requestType,
                                                String url,
                                                IController controller
    ) throws ControllerException {
        controllerMap.put(new ControllerId(requestType, url), controller);
    }

    private final Map<ControllerId, IController> controllerMap;
}
