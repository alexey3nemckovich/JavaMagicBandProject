package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountRestorePassActionMap;
import main.com.bsuir.autoservice.command.ret.PersonalAccountRestorePassRet;
import main.com.bsuir.autoservice.controller.AbstractActionJSONController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountRestorePassController extends AbstractActionJSONController<PersonalAccountRestorePassRet> {

    @Inject
    protected PersonalAccountRestorePassController(@PersonalAccountRestorePassActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }
}
