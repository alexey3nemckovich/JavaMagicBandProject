package main.com.bsuir.autoservice.controller.action;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.ICommandParam;

public class Action {
    public Action(Class<? extends ICommandParam> commandParamClass, ICommand command){
        this.commandParamClass = commandParamClass;
        this.command = command;
    }

    public Class<? extends ICommandParam> getCommandParamClass(){
        return commandParamClass;
    }

    public ICommand getCommand(){
        return command;
    }

    private final Class<? extends ICommandParam> commandParamClass;
    private final ICommand command;
}
