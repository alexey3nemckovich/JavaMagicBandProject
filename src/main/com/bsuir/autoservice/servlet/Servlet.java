package main.com.bsuir.autoservice.servlet;

import com.sun.deploy.net.HttpRequest;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.controller.provider.IControllerProvider;
import main.com.bsuir.autoservice.controller.provider.exception.ControllerProviderException;
import main.com.bsuir.autoservice.controller.provider.impl.DefaultControllerProvider;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    static {
        try {
            controllerProvider = new DefaultControllerProvider(new RequestType[]{RequestType.GET, RequestType.POST});
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static final IControllerProvider controllerProvider;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            invokeRequest(RequestType.POST,request,response);
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            invokeRequest(RequestType.GET,request,response);
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }

    private void invokeRequest(RequestType requestType, HttpServletRequest request, HttpServletResponse response)
            throws ControllerProviderException, ControllerException {
        String url = getUrl(request.getRequestURI());
        IController controller = controllerProvider.getController(requestType,url);
        invokeController(controller,request,response);
    }

    private void invokeController(IController controller, HttpServletRequest request, HttpServletResponse response)
            throws ControllerException {
        Object preparedData = controller.prepareData(request);
        Object resultData = controller.execude(preparedData);
        controller.returnResult(request,response,resultData);
    }

    private String getUrl(String requestURL) {
        return requestURL.replace(".ass","");
    }
}