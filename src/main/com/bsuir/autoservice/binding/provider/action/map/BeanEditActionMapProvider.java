package main.com.bsuir.autoservice.binding.provider.action.map;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.bean.crud.BeanCrudInfo;
import main.com.bsuir.autoservice.command.bean.crud.EditBeanCommand;
import main.com.bsuir.autoservice.command.bean.page.edit.GetBeanEditPageCommand;

public class BeanEditActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanEditActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanCrudInfo.class, injector.getInstance(GetBeanEditPageCommand.class));
        putAction("edit", BeanCrudInfo.class, injector.getInstance(EditBeanCommand.class));
    }
}
