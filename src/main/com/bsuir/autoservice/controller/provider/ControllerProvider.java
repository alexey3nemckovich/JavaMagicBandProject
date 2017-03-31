package main.com.bsuir.autoservice.controller.provider;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.binding.annotation.ControllerProviderArgument;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.ControllerFactory;
import main.com.bsuir.autoservice.controller.provider.exception.ControllerProviderException;
import main.com.bsuir.autoservice.library.RequestType;

import java.util.Map;

public class ControllerProvider{

    @Inject
    public ControllerProvider(@ControllerProviderArgument Map<RequestType,ControllerFactory> requestControllerMap) {
        this.requestControllerMap = requestControllerMap;
    }

    public IController getController(RequestType requestType, String url)
            throws ControllerProviderException {
        try {
            return requestControllerMap.get(requestType).getController(url);
        }catch (Exception e){
            throw new ControllerProviderException(e);
        }
    }

    private final Map<RequestType, ControllerFactory> requestControllerMap;
}
