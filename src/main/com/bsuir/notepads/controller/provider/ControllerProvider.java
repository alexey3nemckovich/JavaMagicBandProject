package main.com.bsuir.notepads.controller.provider;

import com.google.inject.Inject;
import main.com.bsuir.notepads.binding.annotation.ControllerProviderArgument;
import main.com.bsuir.notepads.controller.IController;
import main.com.bsuir.notepads.controller.exception.ControllerException;

import java.util.Map;

public class ControllerProvider implements IControllerProvider{

    @Inject
    public ControllerProvider(@ControllerProviderArgument Map<String,IController> requestControllerMap) {
        this.controllerMap = requestControllerMap;
    }

    public IController getController(String url)
            throws ControllerException {
        try {
            return controllerMap.get(url);
        }catch (Exception e){
            throw new ControllerException(e);
        }
    }

    private final Map<String, IController> controllerMap;
}
