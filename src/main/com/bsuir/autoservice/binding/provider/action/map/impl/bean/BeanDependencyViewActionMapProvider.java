package main.com.bsuir.autoservice.binding.provider.action.map.impl.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.crud.delete.DeleteBeanDependencyCommand;
import main.com.bsuir.autoservice.command.crud.get.GetBeanDependencyViewPageCommand;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;

public class BeanDependencyViewActionMapProvider extends ActionMapProvider{

    @Inject
    private BeanDependencyViewActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanDependencyViewPageInfo.class, injector.getInstance(GetBeanDependencyViewPageCommand.class));
        putAction("delete", BeanDependencyViewPageInfo.class, injector.getInstance(DeleteBeanDependencyCommand.class));
    }
}
