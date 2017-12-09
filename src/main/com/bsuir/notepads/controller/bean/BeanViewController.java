package main.com.bsuir.notepads.controller.bean;

import com.google.inject.Inject;
import main.com.bsuir.notepads.binding.annotation.action.map.BeanViewActionMap;
import main.com.bsuir.notepads.controller.AbstractPageController;
import main.com.bsuir.notepads.controller.action.Action;

import java.util.Map;

public class BeanViewController extends AbstractPageController{

    @Inject
    private BeanViewController(@BeanViewActionMap Map<String, Action> actionMap){
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return "./view.jsp";
    }
}
