package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractJSPController<PrepareDataType,ResultDataType> implements IController<PrepareDataType,ResultDataType> {

    protected abstract void setResultAttributes(HttpServletRequest request, ResultDataType resultData);

    protected abstract String getJspName();

    @Override
    public final void returnResult(HttpServletRequest request, HttpServletResponse response, ResultDataType resultData)
            throws ControllerException {
        try {
            setResultAttributes(request, resultData);
            request.getRequestDispatcher(getJspName()).forward(request, response);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }
}
