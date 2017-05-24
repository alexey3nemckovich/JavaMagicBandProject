package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountOrderDetailsActionMap;
import main.com.bsuir.autoservice.command.ret.PersonalAccountOrderDetailsRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountOrderDetailsController extends AbstractActionPageController<PersonalAccountOrderDetailsRet>{

    private static final String ORDER_DETAILS_JSP = "/account/orderDetails.jsp";

    @Inject
    protected PersonalAccountOrderDetailsController(@PersonalAccountOrderDetailsActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return ORDER_DETAILS_JSP;
    }
}
