package main.com.bsuir.autoservice.binding.provider.action.map.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.crud.add.AddBeanCommand;
import main.com.bsuir.autoservice.command.crud.get.GetBeanAddPageCommand;
import main.com.bsuir.autoservice.command.param.BeanAddPageInfo;

public class BeanDependencyAddActionMapProvider extends ActionMapProvider{

    @Inject
    private BeanDependencyAddActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanAddPageInfo.class, injector.getInstance(GetBeanAddPageCommand.class));
        putAction("add", BeanAddPageInfo.class, injector.getInstance(AddBeanCommand.class));
    }
}
