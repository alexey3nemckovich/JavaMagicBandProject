package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountAddOrderLoadActionMap;
import main.com.bsuir.autoservice.command.ret.PersonalAccountAddOrderLoadRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountAddOrderLoadController extends AbstractActionPageController<PersonalAccountAddOrderLoadRet>{

    private static final String ADD_ORDER_JSP = "/account/orderAdd.jsp";

    @Inject
    protected PersonalAccountAddOrderLoadController(@PersonalAccountAddOrderLoadActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return ADD_ORDER_JSP;
    }
}
