package main.com.bsuir.notepads.controller.bean;

import com.google.inject.Inject;
import main.com.bsuir.notepads.binding.annotation.action.map.BeanAddActionMap;
import main.com.bsuir.notepads.controller.AbstractPageController;
import main.com.bsuir.notepads.controller.action.Action;

import java.util.Map;

public class BeanAddController extends AbstractPageController {
    @Inject
    private BeanAddController(@BeanAddActionMap Map<String, Action> actionMap){
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return "./editForm.jsp";
    }
}
