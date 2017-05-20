package main.com.bsuir.autoservice.command;

import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public abstract class AbstractSessionCommand<ArgumentType extends ICommandParam, ReturnType>
        implements ICommand<ArgumentType, ReturnType>{

    protected final IServiceUnitOfWork serviceUnitOfWork;
    protected final IUserSession session;

    public AbstractSessionCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.session = session;
    }

    @Override
    public final ReturnType execute(ArgumentType param) throws CommandException {
        try{
            return executeImpl(param);
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    protected abstract ReturnType executeImpl(ArgumentType param) throws Exception;
}
