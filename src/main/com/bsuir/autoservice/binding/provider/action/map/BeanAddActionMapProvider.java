package main.com.bsuir.autoservice.binding.provider.action.map;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.bean.crud.AddBeanCommand;
import main.com.bsuir.autoservice.command.bean.crud.BeanCrudInfo;
import main.com.bsuir.autoservice.command.bean.page.add.GetBeanAddPageCommand;

public class BeanAddActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanAddActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanCrudInfo.class, injector.getInstance(GetBeanAddPageCommand.class));
        putAction("add", BeanCrudInfo.class, injector.getInstance(AddBeanCommand.class));
    }
}
