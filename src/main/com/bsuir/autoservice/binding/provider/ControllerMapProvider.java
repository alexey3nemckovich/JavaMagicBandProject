package main.com.bsuir.autoservice.binding.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.bean.BeanCreatePageController;
import main.com.bsuir.autoservice.controller.bean.BeanMainPageController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.controller.bean.BeanTablePageController;
import main.com.bsuir.autoservice.library.RequestType;

import java.util.HashMap;
import java.util.Map;

public class ControllerMapProvider implements Provider<Map<String, IController>> {

    @Inject
    public ControllerMapProvider(Injector injector){
        controllerMap = new HashMap<>();
        initControlMap(injector);
    }

    @Override
    public Map<String, IController> get() {
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
        addControllerForUrl("/bean", injector.getInstance(BeanMainPageController.class));
        addControllerForUrl("/bean/table", injector.getInstance(BeanTablePageController.class));
        addControllerForUrl("/bean/create", injector.getInstance(BeanCreatePageController.class));
    }

    private void addPostRequestControllers(Injector injector)
            throws ControllerException {

    }

    private void addControllerForUrl(String url,
                                     IController controller
    ) throws ControllerException {
        controllerMap.put(url, controller);
    }

    private final Map<String, IController> controllerMap;
}
