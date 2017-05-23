package main.com.bsuir.autoservice.controller.login;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.LoginPageActionMap;
import main.com.bsuir.autoservice.command.ret.NoRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class LoginPageController extends AbstractActionPageController<NoRet> {
    private static final String LOGIN_PAGE_JSP = "/login/loginPage.jsp";

    @Inject
    protected LoginPageController(@LoginPageActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return LOGIN_PAGE_JSP;
    }
}
