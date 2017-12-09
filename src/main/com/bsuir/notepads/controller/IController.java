package main.com.bsuir.notepads.controller;

import main.com.bsuir.notepads.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IController{
    Object invokeCommand(Map<String, String[]> parameters) throws ControllerException;
    void returnResult(HttpServletRequest request, HttpServletResponse response, Object resultData) throws ControllerException;
}
