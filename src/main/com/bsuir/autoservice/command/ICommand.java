package main.com.bsuir.autoservice.command;

import main.com.bsuir.autoservice.command.exception.CommandException;

public interface ICommand<ArgumentType extends ICommandParam, ReturnType> {
    ReturnType execute(ArgumentType param) throws CommandException;
}
