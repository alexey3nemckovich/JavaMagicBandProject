package main.com.bsuir.autoservice.controller.bean;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanDependencyAddActionMap;
import main.com.bsuir.autoservice.controller.AbstractActionPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class BeanDependencyAddController extends AbstractActionPageController {

    @Inject
    private BeanDependencyAddController(@BeanDependencyAddActionMap Map<String, Action> actionMap){
        super(actionMap);
    }

    @Override
    protected String getJspName(){
        return "/bean/editForm.jsp";
    }
}
