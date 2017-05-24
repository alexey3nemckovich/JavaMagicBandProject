package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountUpdateGeneralInformationActionMap;
import main.com.bsuir.autoservice.command.ret.PersonalAccountUpdateGeneralInformationRet;
import main.com.bsuir.autoservice.controller.AbstractActionJSONController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountUpdateGeneralInformationController extends AbstractActionJSONController<PersonalAccountUpdateGeneralInformationRet>{

    @Inject
    protected PersonalAccountUpdateGeneralInformationController(@PersonalAccountUpdateGeneralInformationActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }
}
