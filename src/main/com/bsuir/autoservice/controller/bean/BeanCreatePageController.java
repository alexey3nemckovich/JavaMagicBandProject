package main.com.bsuir.autoservice.controller.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.bean.create.BeanCreatePageInfo;
import main.com.bsuir.autoservice.command.bean.create.GetBeanCreatePageCommand;
import main.com.bsuir.autoservice.controller.AbstractPageController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class BeanCreatePageController extends AbstractPageController {
    @Inject
    private BeanCreatePageController(Injector injector){
        getBeanCreatePageCommand = injector.getInstance(GetBeanCreatePageCommand.class);
    }

    @Override
    public Object onGet(HttpServletRequest request) throws ControllerException {
        try {
            BeanCreatePageInfo beanCreatePageInfo = httpParser.parseParameters(BeanCreatePageInfo.class, request.getParameterMap());
            return getBeanCreatePageCommand.execute(beanCreatePageInfo);
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
        return "./create.jsp";
    }

    private final GetBeanCreatePageCommand getBeanCreatePageCommand;
}
