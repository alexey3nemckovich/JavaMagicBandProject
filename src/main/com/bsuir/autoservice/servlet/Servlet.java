package main.com.bsuir.autoservice.servlet;

import main.com.bsuir.autoservice.command.provider.ICommandProvider;
import main.com.bsuir.autoservice.command.provider.impl.CommandProvider;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    static {
        try {
            commandBinder = new CommandProvider(new RequestType[]{RequestType.GET, RequestType.POST});
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static final ICommandProvider commandBinder;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String url = request.getRequestURI();
            commandBinder.invokeCommand(RequestType.POST,url,request,response);
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String url = getUrl(request.getRequestURI());
            commandBinder.invokeCommand(RequestType.GET,url,request,response);
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }

    private String getUrl(String requestURL) {
        return requestURL.replace(".ass","");
    }
}