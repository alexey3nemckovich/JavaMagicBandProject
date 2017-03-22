package main.com.bsuir.autoservice.command.impl;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.ArrayList;
import java.util.List;

public class BeanMainCommand implements ICommand{
    IServiceUnitOfWork serviceUnitOfWork;

    public BeanMainCommand(IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }


    @Override
    public Object execute(Object data) throws CommandException {
        try {
            List<String> tableNames = new ArrayList<>();
            tableNames.add(serviceUnitOfWork.getUserService().getTableName());
            tableNames.add(serviceUnitOfWork.getOrderService().getTableName());
            return 1;
        }catch (Exception e){
            return new CommandException(e);
        }
    }
}
