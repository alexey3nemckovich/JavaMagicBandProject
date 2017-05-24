package main.com.bsuir.autoservice.binding.provider.action.map.impl.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.crud.add.AddBeanCommand;
import main.com.bsuir.autoservice.command.crud.get.GetBeanAddPageCommand;
import main.com.bsuir.autoservice.command.param.BeanAddPageInfo;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;

public class BeanDependencyAddActionMapProvider extends ActionMapProvider{

    @Inject
    protected BeanDependencyAddActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected Map<String, Action> createBasicMap(Injector injector) {
        return new DefaultHashMap<>(createAction(BeanAddPageInfo.class, injector.getInstance(GetBeanAddPageCommand.class)));
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanAddPageInfo.class, injector.getInstance(GetBeanAddPageCommand.class));
        putAction("add", BeanAddPageInfo.class, injector.getInstance(AddBeanCommand.class));
    }
}
