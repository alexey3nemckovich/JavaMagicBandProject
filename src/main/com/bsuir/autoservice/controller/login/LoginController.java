package main.com.bsuir.autoservice.controller.login;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.LoginActionMap;
import main.com.bsuir.autoservice.command.ret.LoginRet;
import main.com.bsuir.autoservice.controller.AbstractActionJSONController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class LoginController extends AbstractActionJSONController<LoginRet> {

    @Inject
    public LoginController(@LoginActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }
}
