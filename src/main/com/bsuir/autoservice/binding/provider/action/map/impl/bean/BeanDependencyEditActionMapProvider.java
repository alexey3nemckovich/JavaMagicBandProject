package main.com.bsuir.autoservice.binding.provider.action.map.impl.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.crud.edit.EditBeanCommand;
import main.com.bsuir.autoservice.command.crud.get.GetBeanEditPageCommand;
import main.com.bsuir.autoservice.command.param.BeanEditPageInfo;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;

public class BeanDependencyEditActionMapProvider extends ActionMapProvider{

    @Inject
    protected BeanDependencyEditActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected Map<String, Action> createBasicMap(Injector injector) {
        return new DefaultHashMap<>(createAction(BeanEditPageInfo.class, injector.getInstance(GetBeanEditPageCommand.class)));
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanEditPageInfo.class, injector.getInstance(GetBeanEditPageCommand.class));
        putAction("edit", BeanEditPageInfo.class, injector.getInstance(EditBeanCommand.class));
    }
}
