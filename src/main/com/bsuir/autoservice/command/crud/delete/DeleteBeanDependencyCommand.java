package main.com.bsuir.autoservice.command.crud.delete;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.AbstractGetBeanPageCommand;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class DeleteBeanDependencyCommand extends AbstractGetBeanPageCommand implements ICommand<BeanDependencyViewPageInfo>{

    @Inject
    public DeleteBeanDependencyCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanDependencyViewPageInfo execute(BeanDependencyViewPageInfo dependencyViewPageInfo) throws CommandException{
        try {
            IServiceCrud dependencyTableServiceCrud = serviceUnitOfWork.getServiceCrudForBean(dependencyViewPageInfo.dependency.tableName);
            Bean deleteDependency = Bean.getBeanObject(dependencyViewPageInfo.dependency.tableName, dependencyViewPageInfo.fields);
            dependencyTableServiceCrud.delete(deleteDependency);
            dependencyViewPageInfo.result = "Operation success";
            readPage(dependencyViewPageInfo, dependencyTableServiceCrud);
            for(Bean bean : dependencyViewPageInfo.beans){
                dependencyViewPageInfo.dependencyMap.put(bean, dependencyTableServiceCrud.readDependencies(bean));
            }
            return dependencyViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
