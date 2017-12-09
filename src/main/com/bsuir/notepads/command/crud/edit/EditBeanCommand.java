package main.com.bsuir.notepads.command.crud.edit;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.Bean;
import main.com.bsuir.notepads.bean.exception.BeanException;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.command.ICommand;
import main.com.bsuir.notepads.command.exception.CommandException;
import main.com.bsuir.notepads.command.param.BeanEditPageInfo;
import main.com.bsuir.notepads.exception.ExceptionUnwrapper;
import main.com.bsuir.notepads.service.crud.IServiceCrud;
import main.com.bsuir.notepads.service.crud.exception.ServiceException;
import main.com.bsuir.notepads.service.unitOfWork.IServiceUnitOfWork;

public class EditBeanCommand  implements ICommand<BeanEditPageInfo> {

    @Inject
    public EditBeanCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanEditPageInfo execute(BeanEditPageInfo beanEditPageInfo) throws CommandException{
        try {
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(beanEditPageInfo.tableName);
            Bean bean = Bean.getBeanObject(beanEditPageInfo.tableName, beanEditPageInfo.fields);
            serviceCrud.update(bean, beanEditPageInfo.oldFields);
            beanEditPageInfo.result = "Operation success";
            return beanEditPageInfo;
        }catch (ServiceException | BeanException e){
            //log
            beanEditPageInfo.result = String.format(
                    "Failed to edit record: %s",
                    ExceptionUnwrapper.getRootException(e).getMessage()
            );
            return beanEditPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
