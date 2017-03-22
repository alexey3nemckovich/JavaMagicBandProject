package main.com.bsuir.autoservice.command.impl;

import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.ArrayList;
import java.util.List;

public class BeanMainCommand implements ICommand<Object,List<String>>{
    private final IServiceUnitOfWork serviceUnitOfWork;

    public BeanMainCommand(IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }


    @Override
    public List<String> execute(Object data) throws CommandException {
        try {
            List<String> tableNames = new ArrayList<>();
            tableNames.add(serviceUnitOfWork.getUserService().getTableName());
            tableNames.add(serviceUnitOfWork.getOrderService().getTableName());
            return tableNames;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }
}
