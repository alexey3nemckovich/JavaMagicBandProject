package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.controller.action.Action;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;

public abstract class AbstractActionJSONController<R> extends AbstractActionController<R> {

    protected AbstractActionJSONController(Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    public final void returnResultImpl(HttpServletRequest request, HttpServletResponse response, R resultData) throws Exception {
        response.setContentType("application/json");
        response.getWriter().write(convertToJson(resultData));
    }

    protected String convertToJson(R resultData) throws Exception {
        JSONObject jsonObject = new JSONObject();

        for (Field field : resultData.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            jsonObject.append(field.getName(), field.get(resultData));
        }
        return jsonObject.toString();
    }
}
