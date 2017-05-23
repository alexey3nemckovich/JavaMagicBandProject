package main.com.bsuir.autoservice.controller.main;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.GeneralInformationActionMap;
import main.com.bsuir.autoservice.command.ret.GeneralInformationRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class GeneralInformationController extends AbstractActionPageController<GeneralInformationRet> {
    private static final String GENERAL_INFORMATION_JSP = "/indexLoad.jsp";

    @Inject
    protected GeneralInformationController(@GeneralInformationActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return GENERAL_INFORMATION_JSP;
    }
}
