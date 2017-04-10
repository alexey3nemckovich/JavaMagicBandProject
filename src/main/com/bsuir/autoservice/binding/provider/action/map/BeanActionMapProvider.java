package main.com.bsuir.autoservice.binding.provider.action.map;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.param.BeanMainPageInfo;
import main.com.bsuir.autoservice.command.bean.page.main.GetBeanMainPageCommand;

public class BeanActionMapProvider extends ActionMapProvider {

    @Inject
    private BeanActionMapProvider(Injector injector){
        super(injector);
    }

    @Override
    protected void initMap(Injector injector){
        putAction("get", BeanMainPageInfo.class, injector.getInstance(GetBeanMainPageCommand.class));
    }
}
