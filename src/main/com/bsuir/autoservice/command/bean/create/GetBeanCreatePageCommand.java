package main.com.bsuir.autoservice.command.bean.create;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.exception.CommandException;

public class GetBeanCreatePageCommand  implements ICommand<BeanCreatePageInfo> {

    @Override
    public BeanCreatePageInfo execute(BeanCreatePageInfo pageInfo)
            throws CommandException {
        try {
            String beanClassName = getBeanClassName(pageInfo.name);
            Class beanClass = Class.forName(Bean.class.getPackage().getName() + '.' + beanClassName);
            pageInfo.fields = beanClass.getDeclaredFields();
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
