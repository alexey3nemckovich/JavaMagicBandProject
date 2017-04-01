package main.com.bsuir.autoservice.controller.provider;

import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.library.RequestType;

public interface IControllerProvider {
    IController getController(RequestType requestType,String url) throws ControllerException;
}
