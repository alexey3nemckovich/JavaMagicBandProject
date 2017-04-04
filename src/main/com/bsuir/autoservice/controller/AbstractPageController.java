package main.com.bsuir.autoservice.controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import main.com.bsuir.autoservice.binding.AutoServiceShopModule;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.http.parser.IHttpParser;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractPageController implements IController {

    protected abstract String getJspName();
    protected abstract Object onGet(HttpServletRequest request) throws ControllerException;
    protected abstract Object onPut(HttpServletRequest request) throws ControllerException;
    protected abstract Object onPost(HttpServletRequest request) throws ControllerException;
    protected abstract Object onDelete(HttpServletRequest request) throws ControllerException;

    static {
        try {
            Injector injector = Guice.createInjector(new AutoServiceShopModule());
            httpParser =  injector.getInstance(Key.get(IHttpParser.class, Default.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object invokeCommand(HttpServletRequest request) throws ControllerException{
        try {
            switch (RequestType.valueOf(request.getMethod())){
                case GET:
                    return onGet(request);
                case PUT:
                    return onPut(request);
                case POST:
                    return onPost(request);
                default:
                    return onDelete(request);
            }
        }catch (Exception e){
            throw new ControllerException(e);
        }
    }

    @Override
    public final void returnResult(HttpServletRequest request, HttpServletResponse response, Object resultData)
            throws ControllerException {
        try {
            setResultAttributes(request, resultData);
            request.getRequestDispatcher(getJspName()).forward(request, response);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    protected void setResultAttributes(HttpServletRequest request, Object resultData)
            throws ControllerException{
        try {
            for (Field field: resultData.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                request.setAttribute(field.getName(), field.get(resultData));
            }
        }catch (Exception e){
            throw new ControllerException(e);
        }
    }

    protected static IHttpParser httpParser;
}
