package main.com.bsuir.autoservice.command.bean.crud;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.command.param.EditPageInfo;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class EditBeanCommand  implements ICommand<EditPageInfo> {

    @Inject
    public EditBeanCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public EditPageInfo execute(EditPageInfo editPageInfo) throws CommandException{
        try {
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(editPageInfo.tableName);
            Bean bean = Bean.getBeanObject(editPageInfo.tableName, editPageInfo.fields);
            serviceCrud.update(bean, editPageInfo.oldFields);
            editPageInfo.result = "Operation success";
            return editPageInfo;
        }catch (Exception e){
            editPageInfo.result = "Failed to update record.";
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
