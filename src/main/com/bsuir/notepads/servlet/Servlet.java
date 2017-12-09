package main.com.bsuir.notepads.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import main.com.bsuir.notepads.binding.NotepadsModule;
import main.com.bsuir.notepads.command.exception.CommandException;
import main.com.bsuir.notepads.controller.IController;
import main.com.bsuir.notepads.controller.bean.BeanViewController;
import main.com.bsuir.notepads.controller.exception.ControllerException;
import main.com.bsuir.notepads.controller.provider.ControllerProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class Servlet extends HttpServlet {
    static {
        try {
            Injector injector = Guice.createInjector(new NotepadsModule());
            injector.getInstance(BeanViewController.class);
            controllerProvider =  injector.getInstance(ControllerProvider.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            executeRequest(request,response);
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            executeRequest(request, response);
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }

    private void executeRequest(HttpServletRequest request, HttpServletResponse response)
            throws ControllerException, CommandException, UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String url = getUrl(request.getRequestURI());
        IController controller = controllerProvider.getController(url);
        Object resultData = controller.invokeCommand(request.getParameterMap());
        controller.returnResult(request, response, resultData);
    }

    private static final ControllerProvider controllerProvider;
    private String getUrl(String requestURL) {
        return requestURL.replace(".ass","");
    }
}
