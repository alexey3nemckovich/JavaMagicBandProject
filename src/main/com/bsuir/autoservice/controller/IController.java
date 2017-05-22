package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IController<R>{
    R invokeCommand(Map<String, String[]> parameters) throws ControllerException;
    void returnResult(HttpServletRequest request, HttpServletResponse response, R resultData) throws ControllerException;
}
