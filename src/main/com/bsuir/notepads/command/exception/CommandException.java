package main.com.bsuir.notepads.command.exception;

public class CommandException extends Exception {
    public CommandException(Exception e) {
        super(e);
    }
}
