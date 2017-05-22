package main.com.bsuir.autoservice.binding.provider.action.map.impl.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.crud.edit.EditBeanCommand;
import main.com.bsuir.autoservice.command.crud.get.GetBeanEditPageCommand;
import main.com.bsuir.autoservice.command.param.BeanEditPageInfo;

public class BeanEditActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanEditActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanEditPageInfo.class, injector.getInstance(GetBeanEditPageCommand.class));
        putAction("edit", BeanEditPageInfo.class, injector.getInstance(EditBeanCommand.class));
    }
}
