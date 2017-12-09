package main.com.bsuir.notepads.controller;

import com.google.inject.Inject;
import main.com.bsuir.notepads.binding.annotation.action.map.BeanAddActionMap;
import main.com.bsuir.notepads.controller.action.Action;

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
