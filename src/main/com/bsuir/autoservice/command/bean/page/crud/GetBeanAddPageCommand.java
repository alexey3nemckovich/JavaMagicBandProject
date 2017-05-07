package main.com.bsuir.autoservice.command.bean.page.crud;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.config.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class GetBeanAddPageCommand implements ICommand<CrudPageInfo, CrudPageInfo> {

    private final IDatabaseMap databaseMap;

    @Inject
    public GetBeanAddPageCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        this.serviceUnitOfWork = serviceUnitOfWork;
        this.databaseMap = databaseMap;
    }

    @Override
    public CrudPageInfo execute(CrudPageInfo crudPageInfo)
            throws CommandException {
        try {
            Class<? extends Bean> beanClass = databaseMap.getBeanClass(crudPageInfo.tableName);
            Bean bean = beanClass.newInstance();
            crudPageInfo.fields = bean.getFieldValues();
            crudPageInfo.action = "add";
            return crudPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
