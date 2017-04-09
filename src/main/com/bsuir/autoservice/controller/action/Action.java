package main.com.bsuir.autoservice.controller.action;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.ICommandParam;

public class Action {
    public Action(ICommandParam commandParam, ICommand command){
        this.commandParam = commandParam;
        this.command = command;
    }

    public ICommandParam getCommandParam(){
        return commandParam;
    }

    public ICommand getCommand(){
        return command;
    }

    private final ICommandParam commandParam;
    private final ICommand command;
}
