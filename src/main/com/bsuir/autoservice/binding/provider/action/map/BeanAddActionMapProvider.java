package main.com.bsuir.autoservice.binding.provider.action.map;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.bean.crud.AddBeanCommand;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.command.bean.page.crud.GetBeanAddPageCommand;

public class BeanAddActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanAddActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", CrudPageInfo.class, injector.getInstance(GetBeanAddPageCommand.class));
        putAction("add", CrudPageInfo.class, injector.getInstance(AddBeanCommand.class));
    }
}
