package main.com.bsuir.autoservice.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.crud.AbstractGetBeanPageCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class GetBeanDependencyViewPageCommand extends AbstractGetBeanPageCommand
        implements ICommand<BeanDependencyViewPageInfo, BeanDependencyViewPageInfo>{

    @Inject
    public GetBeanDependencyViewPageCommand(IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanDependencyViewPageInfo execute(BeanDependencyViewPageInfo beanDependencyViewPageInfo)
        throws CommandException{
        try {
            ICrudService crudService = serviceUnitOfWork.getCrudService();
            beanDependencyViewPageInfo.beans = crudService.read(
                    beanDependencyViewPageInfo.dependency.tableName,
                    beanDependencyViewPageInfo.dependency.getCondition()
            );
            beanDependencyViewPageInfo.totalPagesCount = getTotalPagesCount(
                    beanDependencyViewPageInfo.countRecords,
                    beanDependencyViewPageInfo.beans.size()
            );
            for(Bean bean : beanDependencyViewPageInfo.beans){
                beanDependencyViewPageInfo.dependencyMap.put(bean,
                        crudService.readDependencies(beanDependencyViewPageInfo.dependency.tableName, bean));
            }
            return beanDependencyViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
