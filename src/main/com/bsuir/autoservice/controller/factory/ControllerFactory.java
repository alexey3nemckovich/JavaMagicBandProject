package main.com.bsuir.autoservice.controller.factory;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.exception.ControllerFactoryException;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;

public class ControllerFactory{

    @Inject
    public ControllerFactory(@Default IController defaultController){
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

    public IController getController(String url) throws ControllerFactoryException {
        try {
            return controllerMap.get(url);
        }catch (Exception e){
            throw new ControllerFactoryException(e);
        }
    }

    private final Map<String,IController> controllerMap;
}
