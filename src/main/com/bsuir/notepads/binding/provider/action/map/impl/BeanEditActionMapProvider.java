package main.com.bsuir.notepads.binding.provider.action.map.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.notepads.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.notepads.command.crud.edit.EditBeanCommand;
import main.com.bsuir.notepads.command.crud.get.GetBeanEditPageCommand;
import main.com.bsuir.notepads.command.param.BeanEditPageInfo;

public class BeanEditActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanEditActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanEditPageInfo.class, injector.getInstance(GetBeanEditPageCommand.class));
        putAction("edit", BeanEditPageInfo.class, injector.getInstance(EditBeanCommand.class));
    }
}
