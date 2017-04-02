package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class NoController extends AbstractJSPController {

    @Override
    public Map prepareData(HttpServletRequest request) throws ControllerException {
        return null;
    }

    @Override
    public ICommand getCommand(HttpServletRequest request) throws ControllerException {
        return null;
    }

    @Override
    protected void setResultAttributes(HttpServletRequest request, Object resultData){

    }

    @Override
    protected String getJspName() {
        return pageName;
    }

    private static final String pageName = "/error.jsp";
}
