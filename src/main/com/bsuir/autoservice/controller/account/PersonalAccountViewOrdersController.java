package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountViewOrdersActionMap;
import main.com.bsuir.autoservice.command.ret.PersonalAccountViewOrdersRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountViewOrdersController extends AbstractActionPageController<PersonalAccountViewOrdersRet>{

    private static final String VIEW_ORDER_PART_JSP = "/account/viewOrderPart.jsp";

    @Inject
    protected PersonalAccountViewOrdersController(@PersonalAccountViewOrdersActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return VIEW_ORDER_PART_JSP;
    }
}
