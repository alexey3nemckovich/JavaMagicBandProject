package main.com.bsuir.notepads.command.param;

import main.com.bsuir.notepads.command.ICommandParam;
import java.util.Map;
import java.util.List;

public class BeanMainPageInfo implements ICommandParam{
    public List<String> dbBeanNames;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params){
        return params;
    }
}
