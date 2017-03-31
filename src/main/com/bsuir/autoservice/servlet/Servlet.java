package main.com.bsuir.autoservice.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.BillingModule;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.controller.provider.ControllerProvider;
import main.com.bsuir.autoservice.controller.provider.exception.ControllerProviderException;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    static {
        try {
            Injector injector = Guice.createInjector(new BillingModule());
            controllerProvider =  injector.getInstance(ControllerProvider.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

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

    @SuppressWarnings("unchecked")
    private void invokeController(IController controller, HttpServletRequest request, HttpServletResponse response)
            throws ControllerException {
        Object preparedData = controller.prepareData(request);
        Object resultData = controller.execute(preparedData);
        controller.returnResult(request,response,resultData);
    }

    private static final ControllerProvider controllerProvider;
    private String getUrl(String requestURL) {
        return requestURL.replace(".ass","");
    }
}
