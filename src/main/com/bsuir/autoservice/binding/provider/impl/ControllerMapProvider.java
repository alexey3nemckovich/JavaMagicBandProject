package main.com.bsuir.autoservice.binding.provider.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.MapProvider;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.bean.*;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

public class ControllerMapProvider extends MapProvider<String, IController> {

    @Inject
    public ControllerMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector) {
        try {
            addGetRequestControllers(injector);
            addPostRequestControllers(injector);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void addGetRequestControllers(Injector injector) throws ControllerException {
        addControllerForUrlAction("/bean", injector.getInstance(BeanController.class));
        addControllerForUrlAction("/bean/add", injector.getInstance(BeanAddController.class));
        addControllerForUrlAction("/bean/view", injector.getInstance(BeanViewController.class));
        addControllerForUrlAction("/bean/edit", injector.getInstance(BeanEditController.class));
    }

    private void addPostRequestControllers(Injector injector)
            throws ControllerException {

    }

    private void addControllerForUrlAction(String url,
                                           IController controller
    ) throws ControllerException {
        map.put(url, controller);
    }
}
