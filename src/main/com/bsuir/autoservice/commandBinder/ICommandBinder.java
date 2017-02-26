package main.com.bsuir.autoservice.commandBinder;

import main.com.bsuir.autoservice.commandBinder.exception.CommandBinderException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommandBinder {
    void invokeCommand(String url, HttpServletRequest request, HttpServletResponse response) throws CommandBinderException;
}
