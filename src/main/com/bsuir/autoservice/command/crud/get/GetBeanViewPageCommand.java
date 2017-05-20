package main.com.bsuir.autoservice.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.AbstractGetBeanPageCommand;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class GetBeanViewPageCommand extends AbstractGetBeanPageCommand
        implements ICommand<BeanViewPageInfo, BeanViewPageInfo> {

    @Inject
    public GetBeanViewPageCommand(IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanViewPageInfo execute(BeanViewPageInfo beanViewPageInfo) throws CommandException {
        try {
            ICrudService crudService = serviceUnitOfWork.getCrudService();
            readPage(beanViewPageInfo, crudService);
            for(Bean bean : beanViewPageInfo.beans){
                beanViewPageInfo.dependencyMap.put(bean, crudService.readDependencies(beanViewPageInfo.tableName, bean));
            }
            return beanViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
