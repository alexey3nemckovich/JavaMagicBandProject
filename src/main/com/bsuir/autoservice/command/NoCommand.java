package main.com.bsuir.autoservice.command;

import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.NoInfo;
import main.com.bsuir.autoservice.command.ret.NoRet;

public class NoCommand implements ICommand<NoInfo, NoRet> {
    @Override
    public NoRet execute(NoInfo param) throws CommandException {
        return new NoRet();
    }
}
