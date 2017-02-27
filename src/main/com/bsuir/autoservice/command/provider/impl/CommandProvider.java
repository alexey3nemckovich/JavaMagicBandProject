package main.com.bsuir.autoservice.command.provider.impl;

import main.com.bsuir.autoservice.library.binder.IBinder;
import main.com.bsuir.autoservice.library.binder.impl.DefaultBinder;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.impl.UserCommand;
import main.com.bsuir.autoservice.command.provider.ICommandProvider;
import main.com.bsuir.autoservice.command.provider.exception.CommandProviderException;
import main.com.bsuir.autoservice.command.factory.ICommandFactory;
import main.com.bsuir.autoservice.command.factory.exception.CommandFactoryException;
import main.com.bsuir.autoservice.command.factory.impl.DefaultCommandFactory;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider implements ICommandProvider {
    public CommandProvider(RequestType[] supportedRequestType) {
        this.requestCommandFactory = createRequestFactory(supportedRequestType);
        registerAllBindings(new DefaultBinder());
    }

    private static Map<RequestType, ICommandFactory> createRequestFactory(RequestType[] supportedRequestType) {
        Map<RequestType, ICommandFactory> map = new HashMap<RequestType, ICommandFactory>();
        for (RequestType requestType : supportedRequestType)
            map.put(requestType, new DefaultCommandFactory());
        return map;
    };

    private void addRequestBind(RequestType requestType, String url, ICommand command) throws CommandFactoryException {
        addMapBind(requestCommandFactory.get(requestType),url,command);
    }

    private void addMapBind(ICommandFactory commandFactory, String url, ICommand command) throws CommandFactoryException {
        commandFactory.addCommand(url, command);
    }

    private void registerAllBindings(IBinder binder) {
        try {
            addGetBind(binder);
            addPostBind(binder);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    private void addPostBind(IBinder binder)  throws CommandFactoryException {
    }

    private void addGetBind(IBinder binder) throws CommandFactoryException {
        addRequestBind(RequestType.GET, "/bean/user",new UserCommand(binder));
    }

    @Override
    public void invokeCommand(RequestType requestType, String url, HttpServletRequest request, HttpServletResponse response)
            throws CommandProviderException {
        try {
            ICommand command = requestCommandFactory.get(requestType).getCommand(url);
            command.execute(request, response);
        }catch (Exception e){
            throw new CommandProviderException(e);
        }
    }

    private final Map<RequestType,ICommandFactory> requestCommandFactory;
}