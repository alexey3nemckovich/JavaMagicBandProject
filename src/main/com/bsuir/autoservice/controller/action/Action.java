package main.com.bsuir.autoservice.controller.action;

import main.com.bsuir.autoservice.command.ICommand;

public class Action {
    public Action(Class commandDataType, ICommand command){
        this.commandDataType = commandDataType;
        this.command = command;
    }

    public Class getCommandDataType(){
        return commandDataType;
    }

    public ICommand getCommand(){
        return command;
    }

    private final Class commandDataType;
    private final ICommand command;
}
