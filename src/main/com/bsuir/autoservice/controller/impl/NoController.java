package main.com.bsuir.autoservice.controller.impl;

import main.com.bsuir.autoservice.controller.AbstractJSPController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class NoController extends AbstractJSPController {
    private static final String pageName = "/error.jsp";

    @Override
    public Object prepareData(HttpServletRequest request) throws ControllerException {
        return null;
    }

    @Override
    public Object execute(Object data) throws ControllerException {
        return null;
    }

    @Override
    protected void setResultAttributes(HttpServletRequest request, Object resultData) {

    }

    @Override
    protected String getJspName() {
        return pageName;
    }
}
