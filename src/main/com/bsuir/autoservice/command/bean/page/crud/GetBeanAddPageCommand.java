package main.com.bsuir.autoservice.command.bean.page.crud;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.lang.reflect.Field;
import java.util.HashMap;

public class GetBeanAddPageCommand implements ICommand<CrudPageInfo> {

    @Inject
    public GetBeanAddPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public CrudPageInfo execute(CrudPageInfo crudPageInfo)
            throws CommandException {
        try {
            Class<? extends Bean> beanClass = (Class<? extends Bean>)Class.forName(Bean.class.getPackage().getName() + '.' + crudPageInfo.tableName);
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
