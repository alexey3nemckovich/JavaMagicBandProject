package main.com.bsuir.autoservice.controller.provider;

import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

public interface IControllerProvider {
    IController getController(String url) throws ControllerException;
}
