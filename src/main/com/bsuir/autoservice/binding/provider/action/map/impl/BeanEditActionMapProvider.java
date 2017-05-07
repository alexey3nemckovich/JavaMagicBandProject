package main.com.bsuir.autoservice.binding.provider.action.map.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.provider.action.map.ActionMapProvider;
import main.com.bsuir.autoservice.command.crud.edit.EditBeanCommand;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.command.crud.get.GetBeanEditPageCommand;
import main.com.bsuir.autoservice.command.param.EditPageInfo;

public class BeanEditActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanEditActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", CrudPageInfo.class, injector.getInstance(GetBeanEditPageCommand.class));
        putAction("edit", EditPageInfo.class, injector.getInstance(EditBeanCommand.class));
    }
}
