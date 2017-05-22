package main.com.bsuir.autoservice.controller.action;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.ICommandParam;

public class Action<CommandParamType extends ICommandParam, ReturnCommandType> {
    public Action(Class<CommandParamType> commandParamClass, ICommand<CommandParamType, ReturnCommandType> command){
        this.commandParamClass = commandParamClass;
        this.command = command;
    }

    public ICommandParam getCommandParamInstance() throws IllegalAccessException, InstantiationException {
        return commandParamClass.newInstance();
    }

    public ICommand<CommandParamType, ReturnCommandType> getCommand(){
        return command;
    }

    private final Class<CommandParamType> commandParamClass;
    private final ICommand<CommandParamType, ReturnCommandType> command;
}
