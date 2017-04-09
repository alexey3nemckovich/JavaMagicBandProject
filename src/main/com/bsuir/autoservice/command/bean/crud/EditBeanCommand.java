package main.com.bsuir.autoservice.command.bean.crud;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class EditBeanCommand  implements ICommand<CrudPageInfo> {

    @Inject
    public EditBeanCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public CrudPageInfo execute(CrudPageInfo crudPageInfo) throws CommandException{
        try {
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(crudPageInfo.tableName);
            Bean bean = Bean.getBeanObject(crudPageInfo.tableName, crudPageInfo.fields);
            serviceCrud.update(bean);
            crudPageInfo.result = "Operation success";
            return crudPageInfo;
        }catch (Exception e){
            crudPageInfo.result = "Failed to update record.";
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
