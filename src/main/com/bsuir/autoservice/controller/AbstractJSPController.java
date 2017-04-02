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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractJSPController<CommandDataObject> implements IController<CommandDataObject> {

    protected abstract String getJspName();
    protected abstract void setResultAttributes(HttpServletRequest request, Object resultData);

    static {
        try {
            Injector injector = Guice.createInjector(new AutoServiceShopModule());
            httpParser =  injector.getInstance(Key.get(IHttpParser.class, Default.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Inject
    public AbstractJSPController(){
        commandDataTypeClass = (Class)((ParameterizedType) getClass().getGenericSuperclass()).
                getActualTypeArguments()[0];
    }

    @Override
    public CommandDataObject prepareData(HttpServletRequest request) throws ControllerException {
        try {
            return httpParser.parseParameters(commandDataTypeClass, request.getParameterMap());
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

    protected static IHttpParser httpParser;
    protected Class<CommandDataObject> commandDataTypeClass;
}
