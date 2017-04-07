package main.com.bsuir.autoservice.controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import javafx.util.Pair;
import main.com.bsuir.autoservice.binding.AutoServiceShopModule;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.http.parser.IHttpParser;
import main.com.bsuir.autoservice.library.RequestType;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractPageController implements IController {

    protected abstract String getJspName();

    static {
        try {
            Injector injector = Guice.createInjector(new AutoServiceShopModule());
            httpParser =  injector.getInstance(Key.get(IHttpParser.class, Default.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    protected AbstractPageController(Map<String, Action> actionMap){
        this.actionMap = actionMap;
    }

    @Override
    public Object invokeCommand(Map<String, String[]> params) throws ControllerException {
        try {
            Action action;
            if(false == params.containsKey("action")) {
                action = actionMap.get("get");
            }else {
                action = actionMap.get(params.get("action")[0]);
            }
            Object object = httpParser.parseParameters(action.getCommandDataType(), params);
            return action.getCommand().execute(object);
        } catch (Exception e){
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

    protected final Map<String, Action> actionMap;
}
