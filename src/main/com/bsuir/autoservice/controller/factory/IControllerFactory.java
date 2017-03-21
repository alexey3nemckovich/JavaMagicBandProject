package main.com.bsuir.autoservice.controller.factory;

import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.exception.ControllerFactoryException;

public interface IControllerFactory {
    void addController(String url, IController contoller) throws ControllerFactoryException;
    void deleteController(String url) throws ControllerFactoryException;
    IController getController(String url) throws ControllerFactoryException;
}
