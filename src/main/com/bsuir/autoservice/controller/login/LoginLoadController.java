package main.com.bsuir.autoservice.controller.login;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.LoginLoadActionMap;
import main.com.bsuir.autoservice.command.ret.LoginLoadRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class LoginLoadController extends AbstractActionPageController<LoginLoadRet> {
    private static final String LOGIN_JSP = "/login/loadLogin.jsp";

    @Inject
    public LoginLoadController(@LoginLoadActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return LOGIN_JSP;
    }
}
