package main.com.bsuir.autoservice.command.bean.crud;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class DeleteBeanCommand implements ICommand<BeanCrudInfo> {

    @Inject
    public DeleteBeanCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanCrudInfo execute(BeanCrudInfo info) throws CommandException {
        try {
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(info.name);
            //Bean bean = Bean.getBeanObject(info.name, info.fieldValues);
            //serviceCrud.delete(bean);
            info.result = "Operation success";
            return info;
        }catch (Exception e){
            info.result = "Failed to delete record.";
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
