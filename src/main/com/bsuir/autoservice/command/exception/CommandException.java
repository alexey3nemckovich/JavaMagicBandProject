package main.com.bsuir.autoservice.command.exception;

public class CommandException extends Exception {
    public CommandException(Exception e) {
        super(e);
    }
}
