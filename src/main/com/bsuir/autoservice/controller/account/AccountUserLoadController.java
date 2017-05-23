package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.AccountUserLoadActionMap;
import main.com.bsuir.autoservice.command.ret.NoRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class AccountUserLoadController extends AbstractActionPageController<NoRet> {

    private static final String ACCOUNT_LOAD_JSP = "/account/userLoad.jsp";

    @Inject
    protected AccountUserLoadController(@AccountUserLoadActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return ACCOUNT_LOAD_JSP;
    }
}
