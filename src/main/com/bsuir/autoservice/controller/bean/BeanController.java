package main.com.bsuir.autoservice.controller.bean;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanActionMap;
import main.com.bsuir.autoservice.controller.AbstractPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class BeanController extends AbstractPageController{
    @Inject
    private BeanController(@BeanActionMap Map<String, Action> actionMap){
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return "./bean.jsp";
    }
}
