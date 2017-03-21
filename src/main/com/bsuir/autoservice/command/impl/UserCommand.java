package main.com.bsuir.autoservice.command.impl;

import main.com.bsuir.autoservice.library.mapper.IMapper;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCommand implements ICommand {
    @Override
    public Object execute(Object data) throws CommandException {
        try {
            //TODO:
            return null;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
