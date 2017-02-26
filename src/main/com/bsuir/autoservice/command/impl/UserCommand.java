package main.com.bsuir.autoservice.command.impl;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, Object parameters) throws CommandException {
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
