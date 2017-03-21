package main.com.bsuir.autoservice.command.factory.impl;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.factory.ICommandFactory;
import main.com.bsuir.autoservice.command.factory.exception.CommandFactoryException;

import java.util.HashMap;
import java.util.Map;

public class DefaultCommandFactory implements ICommandFactory {

    public DefaultCommandFactory(){
        commandMap = new HashMap<>();
    }

    @Override
    public void addCommand(String commandIdentifier, ICommand command) throws CommandFactoryException {
        try {
            commandMap.put(commandIdentifier,command);
        } catch (Exception e) {
            throw new CommandFactoryException(e);
        }
    }

    @Override
    public void deleteCommand(String commandIdentifier) throws CommandFactoryException {
        try {
            if (commandMap.containsKey(commandIdentifier)) {
                commandMap.remove(commandIdentifier);
            }else {
                //TODO: log that not having key
            }
        }
        catch (Exception e){
            throw new CommandFactoryException(e);
        }
    }

    @Override
    public ICommand getCommand(String commandIdentifier) throws CommandFactoryException {
        try {
            return commandMap.get(commandIdentifier);
        }catch (Exception e){
            throw new CommandFactoryException(e);
        }
    }

    private final Map<String,ICommand> commandMap;
}
