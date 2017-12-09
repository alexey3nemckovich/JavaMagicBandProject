package main.com.bsuir.notepads.binding.provider.action.map.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.notepads.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.notepads.command.crud.add.AddBeanCommand;
import main.com.bsuir.notepads.command.crud.get.GetBeanAddPageCommand;
import main.com.bsuir.notepads.command.param.BeanAddPageInfo;

public class BeanAddActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanAddActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanAddPageInfo.class, injector.getInstance(GetBeanAddPageCommand.class));
        putAction("add", BeanAddPageInfo.class, injector.getInstance(AddBeanCommand.class));
    }
}
