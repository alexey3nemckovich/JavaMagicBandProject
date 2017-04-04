package main.com.bsuir.autoservice.command.bean.create;


import main.com.bsuir.autoservice.command.CommandDataTypeRequestParameter;
import java.lang.reflect.Field;


public class BeanCreatePageInfo{
    @CommandDataTypeRequestParameter
    String name;
    Field[] fields;
}
