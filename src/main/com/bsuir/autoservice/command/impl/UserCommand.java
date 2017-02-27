package main.com.bsuir.autoservice.command.impl;

import main.com.bsuir.autoservice.library.binder.IBinder;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCommand implements ICommand {
    public UserCommand(IBinder binder){
        this.binder = binder;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            UserDTO userDTO = (UserDTO) binder.mappedParameters(UserDTO.class, request.getParameterMap());
            request.setAttribute("data",userDTO);
            request.setAttribute("ids",userDTO.id);
            request.getRequestDispatcher("user.jsp").forward(request, response);
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IBinder binder;
}
