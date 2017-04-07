package main.com.bsuir.autoservice.command.bean.page.add;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.bean.crud.BeanCrudInfo;
import main.com.bsuir.autoservice.command.exception.CommandException;

import java.lang.reflect.Field;
import java.util.HashMap;

public class GetBeanAddPageCommand implements ICommand<BeanCrudInfo> {

    @Override
    public BeanCrudInfo execute(BeanCrudInfo pageInfo)
            throws CommandException {
        try {
            String beanClassName = getBeanClassName(pageInfo.name);
            Class beanClass = Class.forName(Bean.class.getPackage().getName() + '.' + beanClassName);
            pageInfo.fields = new HashMap<>();
            for (Field field: beanClass.getDeclaredFields()) {
                pageInfo.fields.put(field.getName(), "");
            }
            pageInfo.action = "add";
            return pageInfo;
        }catch (Exception e){
            throw new CommandException(e);
        }
    }

    private String getBeanClassName(String dbName){
        String[] parts = dbName.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        for(String part : parts){
            stringBuilder.append(part.substring(0,1).toUpperCase() + part.substring(1).toLowerCase());
        }
        return stringBuilder.toString();
    }
}
