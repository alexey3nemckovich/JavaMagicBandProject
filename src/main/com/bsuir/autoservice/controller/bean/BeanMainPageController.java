package main.com.bsuir.autoservice.controller.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.bean.main.BeanMainPageInfo;
import main.com.bsuir.autoservice.command.bean.main.GetBeanMainPageCommand;
import main.com.bsuir.autoservice.controller.AbstractPageController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class BeanMainPageController extends AbstractPageController{
    @Inject
    private BeanMainPageController(Injector injector){
        getBeanMainPageCommand = injector.getInstance(GetBeanMainPageCommand.class);
    }

    @Override
    public Object onGet(HttpServletRequest request) throws ControllerException{
        try {
            return getBeanMainPageCommand.execute(new BeanMainPageInfo());
        } catch (Exception e){
            throw new ControllerException(e);
        }
    }

    @Override
    public Object onPut(HttpServletRequest request) throws ControllerException{
        return null;
    }

    @Override
    public Object onPost(HttpServletRequest request) throws ControllerException{
        return null;
    }

    @Override
    public Object onDelete(HttpServletRequest request) throws ControllerException{
        return null;
    }

    @Override
    protected String getJspName() {
        return "./bean.jsp";
    }

    private final GetBeanMainPageCommand getBeanMainPageCommand;
}
