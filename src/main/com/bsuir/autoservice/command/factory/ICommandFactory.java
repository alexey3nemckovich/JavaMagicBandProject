package main.com.bsuir.autoservice.command.factory;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.factory.exception.CommandFactoryException;

public interface ICommandFactory {
    void addCommand(String commandIdentifier, ICommand command) throws CommandFactoryException;
    void deleteCommand(String commandIdentifier) throws CommandFactoryException;
    ICommand getCommand(String commandIdentifier) throws CommandFactoryException;
}
