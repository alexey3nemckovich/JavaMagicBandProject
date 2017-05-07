package main.com.bsuir.autoservice.command.bean.crud;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.BeanException;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.EditPageInfo;
import main.com.bsuir.autoservice.config.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.exception.ExceptionUnwrapper;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class EditBeanCommand  implements ICommand<EditPageInfo, EditPageInfo> {

    private final IDatabaseMap databaseMap;

    @Inject
    public EditBeanCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.databaseMap = databaseMap;
    }

    @Override
    public EditPageInfo execute(EditPageInfo editPageInfo) throws CommandException{
        try {
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(editPageInfo.tableName);
            Bean bean = databaseMap.getBeanInstance(editPageInfo.tableName, editPageInfo.fields);
            serviceCrud.update(bean, editPageInfo.oldFields);
            editPageInfo.result = "Operation success";
            return editPageInfo;
        }catch (ServiceException | BeanException e){
            //log
            editPageInfo.result = String.format(
                    "Failed to edit record: %s",
                    ExceptionUnwrapper.getRootException(e).getMessage()
            );
            return editPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
