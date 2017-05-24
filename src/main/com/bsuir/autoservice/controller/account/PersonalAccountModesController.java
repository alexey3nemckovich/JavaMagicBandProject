package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountModesActionMap;
import main.com.bsuir.autoservice.command.ret.PersonalAccountModesRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountModesController extends AbstractActionPageController<PersonalAccountModesRet> {

    private static final String ACCOUNT_MODES_JSP = "/account/actionModes.jsp";

    @Inject
    protected PersonalAccountModesController(@PersonalAccountModesActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return ACCOUNT_MODES_JSP;
    }
}
