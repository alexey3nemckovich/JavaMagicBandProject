package main.com.bsuir.notepads.command;

import main.com.bsuir.notepads.command.exception.CommandException;

public interface ICommand<ReturnType extends ICommandParam> {
    ReturnType execute(ReturnType param) throws CommandException;
}
