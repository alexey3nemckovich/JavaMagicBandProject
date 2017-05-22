package main.com.bsuir.autoservice.controller;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.ErrorJspPage;
import main.com.bsuir.autoservice.binding.annotation.action.map.NoActionMap;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class NoController extends AbstractActionPageController {
    private final String errorJspPage;

    @Inject
    public NoController(@ErrorJspPage String errorJspPage,@NoActionMap Map<String, Action> actionMap){
        super(actionMap);
        this.errorJspPage = errorJspPage;
    }

    @Override
    protected String getJspName() {
        return errorJspPage;
    }
}
