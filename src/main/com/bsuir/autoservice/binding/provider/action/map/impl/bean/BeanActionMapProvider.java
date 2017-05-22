package main.com.bsuir.autoservice.binding.provider.action.map.impl.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.crud.get.GetBeanMainPageCommand;
import main.com.bsuir.autoservice.command.param.BeanMainPageInfo;

public class BeanActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanMainPageInfo.class, injector.getInstance(GetBeanMainPageCommand.class));
    }
}
