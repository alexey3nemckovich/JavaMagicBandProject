package main.com.bsuir.autoservice.command;

import main.com.bsuir.autoservice.command.exception.CommandException;

import javax.servlet.ServletRequest;
import java.util.Map;

public interface ICommand<CommandDataType> {
    CommandDataType execute(CommandDataType object) throws CommandException;
}
