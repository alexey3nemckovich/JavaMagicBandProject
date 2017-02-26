package main.com.bsuir.autoservice.servlet;

import main.com.bsuir.autoservice.binder.impl.DefaultBinder;
import main.com.bsuir.autoservice.commandBinder.ICommandBinder;
import main.com.bsuir.autoservice.commandBinder.impl.CommandBinder;
import main.com.bsuir.autoservice.commandFactory.impl.DefaultCommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    static {
        try {
            commandBinder = new CommandBinder(
                    new DefaultCommandFactory(), new DefaultBinder());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static final ICommandBinder commandBinder;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String url = getUrl(request.getRequestURI());
            commandBinder.invokeCommand(url,request,response);
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }

    private String getUrl(String requestURL) {
        return requestURL.replace(".ass","");
    }
}