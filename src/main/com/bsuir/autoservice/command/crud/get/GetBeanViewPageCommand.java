package main.com.bsuir.autoservice.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.AbstractGetBeanPageCommand;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

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
            for(Bean bean : beanViewPageInfo.beans){
                beanViewPageInfo.dependencyMap.put(bean, serviceCrud.readDependencies(bean));
            }
            return beanViewPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
