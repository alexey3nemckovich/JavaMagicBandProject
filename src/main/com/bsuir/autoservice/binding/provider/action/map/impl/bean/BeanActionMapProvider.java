package main.com.bsuir.autoservice.binding.provider.action.map.impl.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.crud.get.GetBeanMainPageCommand;
import main.com.bsuir.autoservice.command.param.BeanMainPageInfo;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;

public class BeanActionMapProvider extends ActionMapProvider {

    @Inject
    protected BeanActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected Map<String, Action> createBasicMap(Injector injector) {
        return new DefaultHashMap<>(createAction(BeanMainPageInfo.class, injector.getInstance(GetBeanMainPageCommand.class)));
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanMainPageInfo.class, injector.getInstance(GetBeanMainPageCommand.class));
    }
}
