package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IController<CommandDataObject>{
    CommandDataObject prepareData(HttpServletRequest request) throws ControllerException;
    ICommand getCommand(HttpServletRequest request) throws ControllerException;
    void returnResult(HttpServletRequest request, HttpServletResponse response, Object resultData) throws ControllerException;
}
