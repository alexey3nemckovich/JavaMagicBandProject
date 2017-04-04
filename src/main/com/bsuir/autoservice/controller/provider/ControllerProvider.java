package main.com.bsuir.autoservice.controller.provider;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.ControllerProviderArgument;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.library.RequestType;
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
