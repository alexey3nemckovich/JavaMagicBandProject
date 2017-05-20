package main.com.bsuir.autoservice.command.crud.delete;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.AbstractGetBeanPageCommand;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class DeleteBeanDependencyCommand extends AbstractGetBeanPageCommand implements ICommand<BeanDependencyViewPageInfo, BeanDependencyViewPageInfo>{

    @Inject
    public DeleteBeanDependencyCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.databaseMap = databaseMap;
    }

    @Override
    public BeanDependencyViewPageInfo execute(BeanDependencyViewPageInfo dependencyViewPageInfo) throws CommandException{
        try {
            ICrudService crudService = serviceUnitOfWork.getCrudService();
            Bean deleteDependency = databaseMap.getBeanInstance(dependencyViewPageInfo.tableName, dependencyViewPageInfo.fields);
            dependencyViewPageInfo.result = crudService.delete(dependencyViewPageInfo.dependency.tableName, deleteDependency)
                    ? "Operation success"
                    : "Operation failure";
            readPage(dependencyViewPageInfo, crudService);
            for(Bean bean : dependencyViewPageInfo.beans){
                dependencyViewPageInfo.dependencyMap.put(bean,
                        crudService.readDependencies(dependencyViewPageInfo.dependency.tableName, bean));
            }
            return dependencyViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
    private final IDatabaseMap databaseMap;
}
