package main.com.bsuir.autoservice.command;

import main.com.bsuir.autoservice.command.exception.CommandException;

import javax.servlet.ServletRequest;
import java.util.Map;

public interface ICommand<ResultDataType> {
    ResultDataType execute(Object data, ServletRequest request) throws CommandException;
}
