package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountViewOrderLoadActionMap;
import main.com.bsuir.autoservice.command.ret.NoRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountViewOrderLoadController extends AbstractActionPageController<NoRet> {

    private static final String VIEW_ORDER_LOAD_JSP = "/account/viewOrderLoad.jsp";

    @Inject
    protected PersonalAccountViewOrderLoadController(@PersonalAccountViewOrderLoadActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return VIEW_ORDER_LOAD_JSP;
    }
}
