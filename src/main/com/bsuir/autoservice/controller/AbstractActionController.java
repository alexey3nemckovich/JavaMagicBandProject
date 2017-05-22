package main.com.bsuir.autoservice.controller;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class AbstractActionController<R> implements IController<R> {

    protected AbstractActionController(Map<String, Action> actionMap) {
        this.actionMap = actionMap;
    }

    private Action getAction(Map<String, String[]> params) {
        return actionMap.get(getUrlAction(params.get("action")));
    }

    private String getUrlAction(String[] actions) {
        return actions == null ? "" : actions[0];
    }

    @Override
    public final R invokeCommand(Map<String, String[]> params) throws ControllerException {
        try {
            Action<ICommandParam, R> action = getAction(params);
            ICommandParam commandParam = action.getCommandParamInstance();
            ICommand<ICommandParam, R> command = action.getCommand();
            commandParam.parse(params);
            return command.execute(commandParam);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public final void returnResult(HttpServletRequest request, HttpServletResponse response, R resultData) throws ControllerException {
        try {
            returnResultImpl(request, response, resultData);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    protected abstract void returnResultImpl(HttpServletRequest request, HttpServletResponse response, R resultData) throws Exception;

    protected final Map<String, Action> actionMap;
}
