package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountViewOrderNumberActionMap;
import main.com.bsuir.autoservice.command.ret.PersonalAccountViewOrderNumberRet;
import main.com.bsuir.autoservice.controller.AbstractActionJSONController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountViewOrderNumberController extends AbstractActionJSONController<PersonalAccountViewOrderNumberRet>{

    @Inject
    protected PersonalAccountViewOrderNumberController(@PersonalAccountViewOrderNumberActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }
}
