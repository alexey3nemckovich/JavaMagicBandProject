package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountMakeOrderActionMap;
import main.com.bsuir.autoservice.command.ret.PersonalAccountMakeOrderRet;
import main.com.bsuir.autoservice.controller.AbstractActionJSONController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountMakeOrderController extends AbstractActionJSONController<PersonalAccountMakeOrderRet>{

    @Inject
    protected PersonalAccountMakeOrderController(@PersonalAccountMakeOrderActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }
}
