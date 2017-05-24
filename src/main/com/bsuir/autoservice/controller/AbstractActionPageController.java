package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractActionPageController<R> extends AbstractActionController<R> {

    protected abstract String getJspName();

    protected AbstractActionPageController(Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    public final void returnResultImpl(HttpServletRequest request, HttpServletResponse response, R resultData) throws Exception {
        setResultAttributes(request, resultData);
        request.getRequestDispatcher(getJspName()).forward(request, response);
    }

    protected void setResultAttributes(HttpServletRequest request, R resultData) throws Exception {
        for (Field field : resultData.getClass().getFields()) {
            field.setAccessible(true);
            request.setAttribute(field.getName(), field.get(resultData));
        }
    }
}
