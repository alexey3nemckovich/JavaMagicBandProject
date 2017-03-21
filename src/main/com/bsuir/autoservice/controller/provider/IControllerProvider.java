package main.com.bsuir.autoservice.controller.provider;

import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.provider.exception.ControllerProviderException;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IControllerProvider {
    IController getController(RequestType requestType, String url) throws ControllerProviderException;
}
