package main.com.bsuir.autoservice.controller.login;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.LogoutActionMap;
import main.com.bsuir.autoservice.command.ret.LogoutRet;
import main.com.bsuir.autoservice.controller.AbstractActionJSONController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class LogoutController extends AbstractActionJSONController<LogoutRet> {

    @Inject
    protected LogoutController(@LogoutActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }
}
