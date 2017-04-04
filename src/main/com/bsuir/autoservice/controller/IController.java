package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IController{
    Object invokeCommand(HttpServletRequest request) throws ControllerException;
    void returnResult(HttpServletRequest request, HttpServletResponse response, Object resultData) throws ControllerException;
}
