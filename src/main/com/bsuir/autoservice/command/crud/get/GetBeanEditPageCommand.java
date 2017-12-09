package main.com.bsuir.autoservice.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanEditPageInfo;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.Map;

public class GetBeanEditPageCommand implements ICommand<BeanEditPageInfo> {

    @Inject
    public GetBeanEditPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanEditPageInfo execute(BeanEditPageInfo crudPageInfo)
            throws CommandException {
        try {
            Class<? extends Bean> beanClass = (Class<? extends Bean>)Class.forName(Bean.class.getPackage().getName() + ".impl." + crudPageInfo.tableName);
            Bean bean = beanClass.newInstance();
            for(String autoField : bean.getAutoGeneratedFields()){
                crudPageInfo.fields.remove(autoField);
            }

            crudPageInfo.action = "edit";
            return crudPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
