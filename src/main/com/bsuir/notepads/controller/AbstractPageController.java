package main.com.bsuir.notepads.controller;

import main.com.bsuir.notepads.command.ICommand;
import main.com.bsuir.notepads.command.ICommandParam;
import main.com.bsuir.notepads.controller.action.Action;
import main.com.bsuir.notepads.controller.exception.ControllerException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractPageController implements IController {

    protected abstract String getJspName();

    @Inject
    protected AbstractPageController(Map<String, Action> actionMap){
        this.actionMap = actionMap;
    }

    @Override
    public Object invokeCommand(Map<String, String[]> params) throws ControllerException {
        try {
            Action action;
            if(!params.containsKey("action")) {
                action = actionMap.get("get");
            }else {
                action = actionMap.get(params.get("action")[0]);
            }
                ICommandParam commandParam = action.getCommandParamClass().newInstance();
            ICommand command = action.getCommand();
            commandParam.parse(params);
            return command.execute(commandParam);
        } catch (Exception e){
            throw new ControllerException(e);
        }
    }

    @Override
    public final void returnResult(HttpServletRequest request, HttpServletResponse response, Object resultData)
            throws ControllerException {
        try {
            setResultAttributes(request, resultData);
            response.setCharacterEncoding("cp1251");
            response.setContentType("text/html;charset=cp1251");
            request.getRequestDispatcher(getJspName()).forward(request, response);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    protected void setResultAttributes(HttpServletRequest request, Object resultData)
            throws ControllerException{
        try {
            for (Field field: resultData.getClass().getFields()) {
                field.setAccessible(true);
                request.setAttribute(field.getName(), field.get(resultData));
            }
        }catch (Exception e){
            throw new ControllerException(e);
        }
    }

    protected final Map<String, Action> actionMap;
}
