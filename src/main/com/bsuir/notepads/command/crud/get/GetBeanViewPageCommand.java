package main.com.bsuir.notepads.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.command.AbstractGetBeanPageCommand;
import main.com.bsuir.notepads.command.ICommand;
import main.com.bsuir.notepads.command.exception.CommandException;
import main.com.bsuir.notepads.command.param.BeanViewPageInfo;
import main.com.bsuir.notepads.service.crud.IServiceCrud;
import main.com.bsuir.notepads.service.unitOfWork.IServiceUnitOfWork;

public class GetBeanViewPageCommand extends AbstractGetBeanPageCommand implements ICommand<BeanViewPageInfo> {

    @Inject
    public GetBeanViewPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanViewPageInfo execute(BeanViewPageInfo beanViewPageInfo)
            throws CommandException {
        try {
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(beanViewPageInfo.tableName);
            readPage(beanViewPageInfo, serviceCrud);
            return beanViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
