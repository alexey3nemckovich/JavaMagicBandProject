package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractJSPController implements IController {

    protected abstract void setResultAttribute(HttpServletRequest request, Object resultData);

    protected abstract String getJspName();

    @Override
    public final void returnResult(HttpServletRequest request, HttpServletResponse response, Object resultData)
            throws ControllerException {
        try {
            setResultAttribute(request, resultData);
            request.getRequestDispatcher(getJspName()).forward(request, response);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }
}
