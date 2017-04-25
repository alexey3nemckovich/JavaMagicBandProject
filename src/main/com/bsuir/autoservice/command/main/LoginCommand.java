package main.com.bsuir.autoservice.command.main;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.LoginInfo;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class LoginCommand implements ICommand<LoginInfo, Boolean> {
    private final IServiceUnitOfWork serviceUnitOfWork;

    @Inject
    public LoginCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public Boolean execute(LoginInfo param) throws CommandException {
        try {
            return serviceUnitOfWork.getUserService().checkLogin(param.getLogin(), param.getPassword());
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
