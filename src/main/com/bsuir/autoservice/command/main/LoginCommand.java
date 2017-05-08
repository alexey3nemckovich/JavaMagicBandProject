package main.com.bsuir.autoservice.command.main;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.LoginInfo;
import main.com.bsuir.autoservice.command.ret.LoginRet;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class LoginCommand implements ICommand<LoginInfo, LoginRet> {
    private final IServiceUnitOfWork serviceUnitOfWork;

    @Inject
    public LoginCommand(IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public LoginRet execute(LoginInfo param) throws CommandException {
        try {
            return new LoginRet(serviceUnitOfWork.getUserService().checkLogin(param.getLogin(), param.getPassword()));
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
