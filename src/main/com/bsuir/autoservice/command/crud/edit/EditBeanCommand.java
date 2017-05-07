package main.com.bsuir.autoservice.command.crud.edit;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanEditPageInfo;
import main.com.bsuir.autoservice.exception.ExceptionUnwrapper;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

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
