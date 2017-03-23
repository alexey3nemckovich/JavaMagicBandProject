package main.com.bsuir.autoservice.controller.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.controller.AbstractJSPController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.dto.UserDTO;
import main.com.bsuir.autoservice.library.mapper.IMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserController extends AbstractJSPController<UserDTO,List<User>>{
    private final IMapper mapper;
    private final ICommand<UserDTO,List<User>> command;

    @Inject
    public UserController(IMapper mapper, @Named("userCommand") ICommand<UserDTO,List<User>> command){
        this.mapper = mapper;
        this.command = command;
    }

    @Override
    public UserDTO prepareData(HttpServletRequest request) throws ControllerException {
        try {
            return (UserDTO)mapper.mappedParameters(UserDTO.class, request.getParameterMap());
        }catch (Exception e){
            throw new ControllerException(e);
        }
    }

    @Override
    public List<User> execute(UserDTO data) throws ControllerException {
        try {
            return command.execute(data);
        }catch (Exception e){
            throw new ControllerException(e);
        }
    }

    @Override
    protected void setResultAttributes(HttpServletRequest request, List<User> resultData) {
        request.setAttribute("data",resultData);
    }

    @Override
    protected String getJspName() {
        return "user.jsp";
    }
}
