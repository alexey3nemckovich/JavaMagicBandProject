package main.com.bsuir.autoservice.controller.account;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.PersonalAccountInformationActionMap;
import main.com.bsuir.autoservice.command.ret.PersonalAccountInformationRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class PersonalAccountInformationController extends AbstractActionPageController<PersonalAccountInformationRet>{
    private static final String GENERAL_INFORMATION_JSP = "/account/generalInformation.jsp";

    @Inject
    protected PersonalAccountInformationController(@PersonalAccountInformationActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return GENERAL_INFORMATION_JSP;
    }
}
