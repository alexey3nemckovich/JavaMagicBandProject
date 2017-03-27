package main.com.bsuir.autoservice.controller.factory.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.IControllerFactory;
import main.com.bsuir.autoservice.controller.factory.exception.ControllerFactoryException;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;

public class DefaultControllerFactory implements IControllerFactory {

    @Inject
    public DefaultControllerFactory(@Named("defaultController") IController defaultController){
        controllerMap = new DefaultHashMap<>(defaultController);
    }

    public void addController(String url, IController controller) throws ControllerFactoryException {
        try {
            controllerMap.put(url,controller);
        } catch (Exception e) {
            throw new ControllerFactoryException(e);
        }
    }

    public void deleteController(String url) throws ControllerFactoryException {
        try {
            if (controllerMap.containsKey(url)) {
                controllerMap.remove(url);
            }else {
                //TODO: log that not having key
            }
        }
        catch (Exception e){
            throw new ControllerFactoryException(e);
        }
    }

    @Override
    public IController getController(String url) throws ControllerFactoryException {
        try {
            return controllerMap.get(url);
        }catch (Exception e){
            throw new ControllerFactoryException(e);
        }
    }

    private final Map<String,IController> controllerMap;
}
