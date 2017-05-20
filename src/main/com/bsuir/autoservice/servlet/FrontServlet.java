package main.com.bsuir.autoservice.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.controller.provider.IControllerProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Singleton
public class FrontServlet extends HttpServlet {

    @Inject
    public FrontServlet(IControllerProvider controllerProvider){
        this.controllerProvider = controllerProvider;
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

    private final IControllerProvider controllerProvider;
    private String getUrl(String requestURL) {
        return requestURL.replace(".ass","");
    }
}
