package main.com.bsuir.autoservice.command;

import main.com.bsuir.autoservice.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
    void execute(HttpServletRequest request, HttpServletResponse response, Object parameters) throws CommandException;
}
