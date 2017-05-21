package main.com.bsuir.autoservice.controller.bean;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanDependencyViewActionMap;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class BeanDependencyViewController extends AbstractActionPageController {

    @Inject
    private BeanDependencyViewController(@BeanDependencyViewActionMap Map<String, Action> actionMap){
        super(actionMap);
    }

    @Override
    protected String getJspName(){
        return "./view.jsp";
    }
}
