package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IController<PrepareDataType,ResultDataType>{
    PrepareDataType prepareData(HttpServletRequest request) throws ControllerException;
    ResultDataType execute(PrepareDataType data) throws ControllerException;
    void returnResult(HttpServletRequest request, HttpServletResponse response, ResultDataType resultData) throws ControllerException;
}
