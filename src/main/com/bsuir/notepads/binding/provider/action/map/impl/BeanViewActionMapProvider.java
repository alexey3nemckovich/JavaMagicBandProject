package main.com.bsuir.notepads.binding.provider.action.map.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.notepads.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.notepads.command.crud.delete.DeleteBeanCommand;
import main.com.bsuir.notepads.command.param.BeanViewPageInfo;
import main.com.bsuir.notepads.command.crud.get.GetBeanViewPageCommand;


public class BeanViewActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanViewActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanViewPageInfo.class, injector.getInstance(GetBeanViewPageCommand.class));
        putAction("delete", BeanViewPageInfo.class, injector.getInstance(DeleteBeanCommand.class));
    }
}
