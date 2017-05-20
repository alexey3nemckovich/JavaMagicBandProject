package main.com.bsuir.autoservice.command.crud.add;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanAddPageInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.exception.ExceptionUnwrapper;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

public class AddBeanCommand implements ICommand<BeanAddPageInfo, BeanAddPageInfo>{

    @Inject
    public AddBeanCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.databaseMap = databaseMap;
    }

    @Override
    public BeanAddPageInfo execute(BeanAddPageInfo crudPageInfo) throws CommandException{
        try {
            ICrudService crudService = serviceUnitOfWork.getCrudService();
            Bean bean = databaseMap.getBeanInstance(crudPageInfo.tableName, crudPageInfo.fields);
            crudPageInfo.result = crudService.create(crudPageInfo.tableName, bean)
                    ? "Operation success"
                    : "Operation failure";
            return crudPageInfo;
        }catch (ServiceException | BeanException e){
            //log
            crudPageInfo.result = String.format(
                    "Failed to add new '%s': %s.",
                    crudPageInfo.tableName,
                    ExceptionUnwrapper.getRootException(e).getMessage()
            );
            return crudPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
    private final IDatabaseMap databaseMap;
}
