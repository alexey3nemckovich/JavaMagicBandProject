package main.com.bsuir.autoservice.command.crud.delete;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.crud.AbstractGetBeanPageCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.exception.ExceptionUnwrapper;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class DeleteBeanCommand extends AbstractGetBeanPageCommand implements ICommand<BeanViewPageInfo, BeanViewPageInfo> {

    private final IDatabaseMap databaseMap;

    @Inject
    public DeleteBeanCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.databaseMap = databaseMap;
    }

    @Override
    public BeanViewPageInfo execute(BeanViewPageInfo beanViewPageInfo) throws CommandException {
        try {
            ICrudService crudService = serviceUnitOfWork.getCrudService();
            Bean deleteBean = databaseMap.getBeanInstance(beanViewPageInfo.tableName, beanViewPageInfo.fields);
            beanViewPageInfo.result = crudService.delete(beanViewPageInfo.tableName, deleteBean)
                    ? "Operation success"
                    : "Operation failure";
            readPage(beanViewPageInfo, crudService);
            for(Bean bean : beanViewPageInfo.beans){
                beanViewPageInfo.dependencyMap.put(bean, crudService.readDependencies(beanViewPageInfo.tableName, bean));
            }
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
