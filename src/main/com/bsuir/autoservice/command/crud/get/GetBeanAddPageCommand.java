package main.com.bsuir.autoservice.command.crud.get;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanAddPageInfo;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.Map;

public class GetBeanAddPageCommand implements ICommand<BeanAddPageInfo> {

    @Inject
    public GetBeanAddPageCommand(@Default IServiceUnitOfWork serviceUnitOfWork){
        this.serviceUnitOfWork = serviceUnitOfWork;
    }

    @Override
    public BeanAddPageInfo execute(BeanAddPageInfo beanAddPageInfo)
            throws CommandException {
        try {
            Class<? extends Bean> beanClass = (Class<? extends Bean>)Class.forName(Bean.class.getPackage().getName() + ".impl." + beanAddPageInfo.tableName);
            Bean bean = beanClass.newInstance();
            beanAddPageInfo.fields = bean.getFieldValues();
            for(Map.Entry<String, String> entry : beanAddPageInfo.defaultValues.entrySet()){
                beanAddPageInfo.fields.put(entry.getKey(), entry.getValue());
            }

            beanAddPageInfo.action = "add";
            return beanAddPageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private final IServiceUnitOfWork serviceUnitOfWork;
}
