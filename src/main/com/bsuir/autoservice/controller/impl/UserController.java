package main.com.bsuir.autoservice.controller.impl;

import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.controller.AbstractJSPController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.dto.UserDTO;
import main.com.bsuir.autoservice.library.mapper.IMapper;

import javax.servlet.http.HttpServletRequest;

public class UserController extends AbstractJSPController<UserDTO,User>{
    private final IMapper mapper;
    private final ICommand<UserDTO,User> command;

    public UserController(IMapper mapper, ICommand<UserDTO,User> command){
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
    public User execute(UserDTO data) throws ControllerException {
        try {
            return command.execute(data);
        }catch (Exception e){
            throw new ControllerException(e);
        }
    }

    @Override
    protected void setResultAttributes(HttpServletRequest request, User resultData) {
        request.setAttribute("data",resultData);
    }

    @Override
    protected String getJspName() {
        return "user.jsp";
    }
}
