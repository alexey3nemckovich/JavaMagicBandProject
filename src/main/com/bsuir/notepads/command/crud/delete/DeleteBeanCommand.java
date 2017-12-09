package main.com.bsuir.notepads.command.crud.delete;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.Bean;
import main.com.bsuir.notepads.bean.exception.BeanException;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.command.AbstractGetBeanPageCommand;
import main.com.bsuir.notepads.command.ICommand;
import main.com.bsuir.notepads.command.exception.CommandException;
import main.com.bsuir.notepads.command.param.BeanViewPageInfo;
import main.com.bsuir.notepads.exception.ExceptionUnwrapper;
import main.com.bsuir.notepads.service.crud.IServiceCrud;
import main.com.bsuir.notepads.service.crud.exception.ServiceException;
import main.com.bsuir.notepads.service.unitOfWork.IServiceUnitOfWork;

public class DeleteBeanCommand extends AbstractGetBeanPageCommand implements ICommand<BeanViewPageInfo> {

    @Inject
    public DeleteBeanCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanViewPageInfo execute(BeanViewPageInfo beanViewPageInfo) throws CommandException {
        try {
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(beanViewPageInfo.tableName);
            Bean deleteBean = Bean.getBeanObject(beanViewPageInfo.tableName, beanViewPageInfo.fields);
            serviceCrud.delete(deleteBean);
            beanViewPageInfo.result = "Operation success";
            readPage(beanViewPageInfo, serviceCrud);
            return beanViewPageInfo;
        }catch (ServiceException | BeanException e){
            //log
            beanViewPageInfo.result = String.format(
                    "Failed to delete record: %s",
                    ExceptionUnwrapper.getRootException(e).getMessage()
            );
            return beanViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
