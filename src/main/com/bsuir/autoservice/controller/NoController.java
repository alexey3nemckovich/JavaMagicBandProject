package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class NoController extends AbstractPageController {

    @Override
    public Object onGet(HttpServletRequest request) throws ControllerException{
        return null;
    }

    @Override
    public Object onPut(HttpServletRequest request) throws ControllerException{
        return null;
    }

    @Override
    public Object onPost(HttpServletRequest request) throws ControllerException{
        return null;
    }

    @Override
    public Object onDelete(HttpServletRequest request) throws ControllerException{
        return null;
    }

    @Override
    protected String getJspName() {
        return pageName;
    }

    private static final String pageName = "/error.jsp";
}
