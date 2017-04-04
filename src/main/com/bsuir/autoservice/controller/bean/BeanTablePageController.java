package main.com.bsuir.autoservice.controller.bean;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.bean.table.BeanTablePageInfo;
import main.com.bsuir.autoservice.command.bean.table.GetBeanTablePageCommand;
import main.com.bsuir.autoservice.controller.AbstractPageController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class BeanTablePageController extends AbstractPageController{

    @Inject
    private BeanTablePageController(Injector injector){
        getBeanTablePageCommand = injector.getInstance(GetBeanTablePageCommand.class);
    }

    @Override
    public Object onGet(HttpServletRequest request) throws ControllerException{
        try {
            BeanTablePageInfo beanTablePageInfo = httpParser.parseParameters(BeanTablePageInfo.class, request.getParameterMap());
            return getBeanTablePageCommand.execute(beanTablePageInfo);
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
        return "./table.jsp";
    }

    private final GetBeanTablePageCommand getBeanTablePageCommand;
}
