package main.com.bsuir.autoservice.controller.bean.view;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.bean.page.BeanPageInfo;
import main.com.bsuir.autoservice.command.bean.page.GetBeanPageCommand;
import main.com.bsuir.autoservice.controller.AbstractJSPController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class BeanViewController extends AbstractJSPController<BeanPageInfo> {

    @Inject
    private BeanViewController(Injector injector){
        getBeanPageCommand = injector.getInstance(GetBeanPageCommand.class);
    }

    @Override
    public ICommand getCommand(HttpServletRequest request)
            throws ControllerException{
        return getBeanPageCommand;
    }

    @Override
    protected String getJspName() {
        return "./bean.jsp";
    }

    private final GetBeanPageCommand getBeanPageCommand;
}
