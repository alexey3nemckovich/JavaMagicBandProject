package main.com.bsuir.autoservice.binding.provider.action.map;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.bean.crud.DeleteBeanCommand;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.command.bean.page.view.GetBeanViewPageCommand;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;


public class BeanViewActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanViewActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanViewPageInfo.class, injector.getInstance(GetBeanViewPageCommand.class));
        putAction("delete", BeanViewPageInfo.class, injector.getInstance(DeleteBeanCommand.class));
    }
}
