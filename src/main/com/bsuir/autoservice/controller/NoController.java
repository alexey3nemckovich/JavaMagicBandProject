package main.com.bsuir.autoservice.controller;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanAddActionMap;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import java.util.Map;

public class NoController extends AbstractPageController {

    @Inject
    private NoController(@BeanAddActionMap Map<String, Action> actionMap){
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return "./error.jsp";
    }
}
