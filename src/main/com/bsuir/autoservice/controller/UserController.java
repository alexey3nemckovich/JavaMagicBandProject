package main.com.bsuir.autoservice.controller;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.User.GetAllUserCommand;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.http.HttpServletRequest;

public class UserController extends AbstractJSPController<User>{
    @Inject
    private UserController(Injector injector){
        getAllUserCommand = injector.getInstance(GetAllUserCommand.class);
    }

    @Override
    public ICommand getCommand(HttpServletRequest request)
            throws ControllerException{
        RequestType requestType = RequestType.valueOf(request.getMethod());
        if(RequestType.GET == requestType)
        {
            return getAllUserCommand;
        }else{
            return null;
        }
    }

    @Override
    protected void setResultAttributes(HttpServletRequest request, Object resultData) {
        request.setAttribute("beans", resultData);
    }

    @Override
    protected String getJspName() {
        return "./user.jsp";
    }

    private final GetAllUserCommand getAllUserCommand;
}
