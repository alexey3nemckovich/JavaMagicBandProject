package main.com.bsuir.autoservice.binding.provider.action.map.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.bean.page.dependence.GetBeanDependencyViewPageCommand;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;

public class BeanDependencyViewActionMapProvider extends ActionMapProvider{

    @Inject
    private BeanDependencyViewActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanDependencyViewPageInfo.class, injector.getInstance(GetBeanDependencyViewPageCommand.class));
    }
}
