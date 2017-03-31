package main.com.bsuir.autoservice.controller.provider.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.IControllerFactory;
import main.com.bsuir.autoservice.controller.provider.IControllerProvider;
import main.com.bsuir.autoservice.controller.provider.exception.ControllerProviderException;
import main.com.bsuir.autoservice.library.RequestType;

import java.util.Map;

public class DefaultControllerProvider implements IControllerProvider {
    private final Map<RequestType, IControllerFactory> requestControllerMap;

    @Inject
    public DefaultControllerProvider(@Named("mapControllerFactory") Map<RequestType,IControllerFactory> requestControllerMap) {
        this.requestControllerMap = requestControllerMap;
     }

    @Override
    public IController getController(RequestType requestType, String url)
            throws ControllerProviderException {
        try {
            return requestControllerMap.get(requestType).getController(url);
        }catch (Exception e){
            throw new ControllerProviderException(e);
        }
    }
}
