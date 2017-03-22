package main.com.bsuir.autoservice.command.impl;

import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.dto.UserDTO;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.List;

public class UserCommand implements ICommand<UserDTO,List<User>> {
    private final IServiceUnitOfWork serviceUnitOfWork;

    public UserCommand(IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public List<User> execute(UserDTO userDTO) throws CommandException {
        try {
            return serviceUnitOfWork.getUserService().read(userDTO.id,1);
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
