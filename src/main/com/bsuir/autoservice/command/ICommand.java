package main.com.bsuir.autoservice.command;

import main.com.bsuir.autoservice.command.exception.CommandException;

public interface ICommand<PrepareDataType, ResultDataType> {
    ResultDataType execute(PrepareDataType data) throws CommandException;
}
