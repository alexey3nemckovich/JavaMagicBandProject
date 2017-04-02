package main.com.bsuir.autoservice.command.User;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.dto.UserDTO;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.List;
import java.util.Map;

public class GetAllUserCommand implements ICommand<List<User>>{
    private final IServiceUnitOfWork serviceUnitOfWork;

    @Inject
    public GetAllUserCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public List<User> execute(Object data)
            throws CommandException {
        try {
            return serviceUnitOfWork.getUserService().read(0,5);
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
