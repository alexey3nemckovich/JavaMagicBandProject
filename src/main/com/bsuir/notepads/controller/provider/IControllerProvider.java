package main.com.bsuir.notepads.controller.provider;

import main.com.bsuir.notepads.controller.IController;
import main.com.bsuir.notepads.controller.exception.ControllerException;

public interface IControllerProvider {
    IController getController(String url) throws ControllerException;
}
