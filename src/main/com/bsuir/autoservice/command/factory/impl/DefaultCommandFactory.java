package main.com.bsuir.autoservice.command.factory.impl;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.impl.NoCommand;
import main.com.bsuir.autoservice.command.factory.ICommandFactory;
import main.com.bsuir.autoservice.command.factory.exception.CommandFactoryException;
import main.com.bsuir.autoservice.library.DefaultHashMap;

import java.util.Map;

public class DefaultCommandFactory implements ICommandFactory {

    public DefaultCommandFactory(){
        commandMap = new DefaultHashMap<>(new NoCommand());
    }

    public void addCommand(String url, ICommand command) throws CommandFactoryException {
        try {
            commandMap.put(url,command);
        } catch (Exception e) {
            throw new CommandFactoryException(e);
        }
    }

    public void deleteCommand(String url) throws CommandFactoryException {
        try {
            if (commandMap.containsKey(url)) {
                commandMap.remove(url);
            }else {
                //TODO: log that not having key
            }
        }
        catch (Exception e){
            throw new CommandFactoryException(e);
        }
    }

    @Override
    public ICommand getCommand(String url) throws CommandFactoryException {
        try {
            return commandMap.get(url);
        }catch (Exception e){
            throw new CommandFactoryException(e);
        }
    }

    private final Map<String,ICommand> commandMap;
}
