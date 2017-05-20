package main.com.bsuir.autoservice.command.main;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.ResetLoginInfo;
import main.com.bsuir.autoservice.command.ret.ResetLoginRet;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class ResetLoginCommand implements ICommand<ResetLoginInfo, ResetLoginRet>{

    private final IServiceUnitOfWork serviceUnitOfWork;

    @Inject
    public ResetLoginCommand(IServiceUnitOfWork serviceUnitOfWork) {
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public ResetLoginRet execute(ResetLoginInfo param) throws CommandException {
        try {
            return new ResetLoginRet(serviceUnitOfWork.getUserService().resetLogin(param.getEmail()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
