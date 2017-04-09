package main.com.bsuir.autoservice.binding.provider.action.map;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.bean.crud.EditBeanCommand;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.command.bean.page.crud.GetBeanEditPageCommand;

public class BeanEditActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanEditActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", injector.getInstance(CrudPageInfo.class), injector.getInstance(GetBeanEditPageCommand.class));
        putAction("edit", injector.getInstance(CrudPageInfo.class), injector.getInstance(EditBeanCommand.class));
    }
}
