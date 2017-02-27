package main.com.bsuir.autoservice.commandBinder.impl;

import main.com.bsuir.autoservice.binder.IBinder;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.impl.UserCommand;
import main.com.bsuir.autoservice.commandBinder.ICommandBinder;
import main.com.bsuir.autoservice.commandBinder.exception.CommandBinderException;
import main.com.bsuir.autoservice.commandFactory.ICommandFactory;
import main.com.bsuir.autoservice.commandFactory.exception.CommandFactoryException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandBinder implements ICommandBinder {
    public CommandBinder(ICommandFactory commandFactory, IBinder binder) {
        this.commandFactory = commandFactory;
        this.binder = binder;
        registerAllBindings();
    }

    private void addMapBind(String url, ICommand command) throws CommandFactoryException {
        commandFactory.addCommand(url, command);
    }

    private void registerAllBindings() {
        try {
            addMapBind("/bean/user",new UserCommand(binder));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public void invokeCommand(String url, HttpServletRequest request, HttpServletResponse response) throws CommandBinderException {
        try {
            ICommand command = commandFactory.getCommand(url);
            command.execute(request, response);
        }catch (Exception e){
            throw new CommandBinderException(e);
        }
    }

    private final ICommandFactory commandFactory;
    private final IBinder binder;
}
