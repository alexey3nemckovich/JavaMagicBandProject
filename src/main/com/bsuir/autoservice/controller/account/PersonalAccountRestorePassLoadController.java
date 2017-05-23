package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountRestorePassLoadActionMap;
import main.com.bsuir.autoservice.command.ret.NoRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountRestorePassLoadController extends AbstractActionPageController<NoRet> {

    private static final String RESTORE_PASS_JSP = "/account/restorePass.jsp";

    @Inject
    protected PersonalAccountRestorePassLoadController(@PersonalAccountRestorePassLoadActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }


    @Override
    protected String getJspName() {
        return RESTORE_PASS_JSP;
    }
}
