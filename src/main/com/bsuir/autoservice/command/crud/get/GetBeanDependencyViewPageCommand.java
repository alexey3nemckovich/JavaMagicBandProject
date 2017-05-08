package main.com.bsuir.autoservice.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.AbstractGetBeanPageCommand;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class GetBeanDependencyViewPageCommand extends AbstractGetBeanPageCommand implements ICommand<BeanDependencyViewPageInfo>{

    @Inject
    public GetBeanDependencyViewPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanDependencyViewPageInfo execute(BeanDependencyViewPageInfo beanDependencyViewPageInfo)
        throws CommandException{
        try {
            IServiceCrud dependencyTableServiceCrud = serviceUnitOfWork.getServiceCrudForBean(
                    beanDependencyViewPageInfo.dependency.tableName
            );
            beanDependencyViewPageInfo.beans = dependencyTableServiceCrud.read(
                    beanDependencyViewPageInfo.dependency.getCondition()
            );
            beanDependencyViewPageInfo.totalPagesCount = getTotalPagesCount(
                    beanDependencyViewPageInfo.countRecords,
                    beanDependencyViewPageInfo.beans.size()
            );
            return beanDependencyViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
