package main.com.bsuir.autoservice.command.impl;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.dto.UserDTO;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class UserCommand implements ICommand {
    private final IServiceUnitOfWork serviceUnitOfWork;

    public UserCommand(IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public Object execute(Object data) throws CommandException {
        try {
            UserDTO userDTO = (UserDTO)data;
            return serviceUnitOfWork.getUserService().read(userDTO.id,1);
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
