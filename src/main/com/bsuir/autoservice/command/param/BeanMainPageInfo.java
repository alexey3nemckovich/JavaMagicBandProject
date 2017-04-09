package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;
import java.util.Map;
import java.util.List;

public class BeanMainPageInfo implements ICommandParam{
    public List<String> dbBeanNames;

    @Override
    public BeanMainPageInfo parse(Map<String, String[]> params){
        return this;
    }
}
