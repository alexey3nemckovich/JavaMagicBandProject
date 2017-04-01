package main.com.bsuir.autoservice.controller.provider;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.ControllerProviderArgument;
import main.com.bsuir.autoservice.controller.ControllerId;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.library.RequestType;
import java.util.Map;

public class ControllerProvider implements IControllerProvider{

    @Inject
    public ControllerProvider(@ControllerProviderArgument Map<ControllerId,IController> requestControllerMap) {
        this.requestControllerMap = requestControllerMap;
    }

    public IController getController(RequestType requestType, String url)
            throws ControllerException {
        try {
            return requestControllerMap.get(new ControllerId(requestType, url));
        }catch (Exception e){
            throw new ControllerException(e);
        }
    }

    private final Map<ControllerId, IController> requestControllerMap;
}
