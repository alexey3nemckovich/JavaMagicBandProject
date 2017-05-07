package main.com.bsuir.autoservice.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.AbstractGetBeanPageCommand;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;
import main.com.bsuir.autoservice.service.Dependency;
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
            IServiceCrud serviceCrud = serviceUnitOfWork.getServiceCrudForBean(beanDependencyViewPageInfo.tableName);
            Bean bean = Bean.getBeanObject(beanDependencyViewPageInfo.tableName, beanDependencyViewPageInfo.fields);
            Dependency dependency = (Dependency)serviceCrud.readDependencies(bean).get(beanDependencyViewPageInfo.dependencyTableName);
            beanDependencyViewPageInfo.beans = dependency.beans;
            beanDependencyViewPageInfo.dependencyField = dependency.name;
            beanDependencyViewPageInfo.totalPagesCount = getTotalPagesCount(beanDependencyViewPageInfo.countRecords, serviceCrud);
            return beanDependencyViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
