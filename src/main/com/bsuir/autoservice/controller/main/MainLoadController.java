package main.com.bsuir.autoservice.controller.main;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.MainLoadActionMap;
import main.com.bsuir.autoservice.command.ret.NoRet;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class MainLoadController extends AbstractActionPageController<NoRet> {
    private static final String MAIN_LOAD_JSP = "/index.jsp";

    @Inject
    protected MainLoadController(@MainLoadActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return MAIN_LOAD_JSP;
    }
}
