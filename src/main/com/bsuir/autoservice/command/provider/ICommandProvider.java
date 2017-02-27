package main.com.bsuir.autoservice.command.provider;

import main.com.bsuir.autoservice.command.provider.exception.CommandProviderException;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommandProvider {
    void invokeCommand(RequestType requestType, String url, HttpServletRequest request, HttpServletResponse response) throws CommandProviderException;
}
