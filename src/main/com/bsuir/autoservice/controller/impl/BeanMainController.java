package main.com.bsuir.autoservice.controller.impl;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.controller.AbstractJSPController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class BeanMainController extends AbstractJSPController{
    private static final String jspName = "/bean/main.jsp";
    private final ICommand command;

    public BeanMainController(ICommand command){
        this.command = command;
    }


    @Override
    public Object prepareData(HttpServletRequest request) throws ControllerException {
        return null;
    }

    @Override
    public Object execute(Object data) throws ControllerException {
        try {
            return command.execute(data);
        } catch (Exception e){
            throw new ControllerException(e);
        }
    }

    @Override
    protected void setResultAttributes(HttpServletRequest request, Object resultData) {
        request.setAttribute("tables", resultData);
    }

    @Override
    protected String getJspName() {
        return jspName;
    }
}
