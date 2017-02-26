package main.com.bsuir.autoservice.commandFactory;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.commandFactory.exception.CommandFactoryException;

public interface ICommandFactory {
    void addCommand(String url, ICommand command) throws CommandFactoryException;
    void deleteCommand(String url) throws CommandFactoryException;
    ICommand getCommand(String url) throws CommandFactoryException;
}
