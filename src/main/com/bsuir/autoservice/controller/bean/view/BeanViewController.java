package main.com.bsuir.autoservice.controller.bean.view;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.User.GetUserPageCommand;
import main.com.bsuir.autoservice.controller.AbstractJSPController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

public class BeanViewController extends AbstractJSPController<PageInfo> {
    @Inject
    private BeanViewController(Injector injector){
        getUserPageCommand = injector.getInstance(GetUserPageCommand.class);
    }

    @Override
    public PageInfo prepareData(HttpServletRequest request) throws ControllerException {
        try {
            PageInfo pageInfo = httpParser.parseParameters(PageInfo.class, request.getParameterMap());
            return pageInfo;
        }catch (Exception e){
            throw new ControllerException(e);
        }
    }

    @Override
    public ICommand getCommand(HttpServletRequest request)
            throws ControllerException{
        return getUserPageCommand;
    }

    @Override
    protected void setResultAttributes(HttpServletRequest request, Object resultData) {
        request.setAttribute("beans", resultData);
    }

    @Override
    protected String getJspName() {
        return "./user.jsp";
    }

    private final GetUserPageCommand getUserPageCommand;
}
