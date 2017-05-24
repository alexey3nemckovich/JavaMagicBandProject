package main.com.bsuir.autoservice.binding.provider.action.map.impl.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.crud.delete.DeleteBeanCommand;
import main.com.bsuir.autoservice.command.crud.get.GetBeanViewPageCommand;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;


public class BeanViewActionMapProvider extends ActionMapProvider {

    @Inject
    protected BeanViewActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected Map<String, Action> createBasicMap(Injector injector) {
        return new DefaultHashMap<>(createAction(BeanViewPageInfo.class, injector.getInstance(GetBeanViewPageCommand.class)));
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanViewPageInfo.class, injector.getInstance(GetBeanViewPageCommand.class));
        putAction("delete", BeanViewPageInfo.class, injector.getInstance(DeleteBeanCommand.class));
    }
}
