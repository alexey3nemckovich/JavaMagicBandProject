package main.com.bsuir.autoservice.controller.bean;

import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.controller.jsp.AbstractJSPController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class BeanTableController extends AbstractJSPController{
    private static final String jspName = "/bean/table.jsp";

    @Override
    public Object prepareData(HttpServletRequest request) throws ControllerException {
        return request.getParameter("table");
    }

    @Override
    public Object execute(Object data) throws ControllerException {
        try {
            //TODO: getResult
            return data;
        }catch (Exception e){
            return new CommandException(e);
        }
    }

    @Override
    protected void setResultAttributes(HttpServletRequest request, Object resultData) {
        request.setAttribute("data",resultData);
    }

    @Override
    protected String getJspName() {
        return jspName;
    }
}
