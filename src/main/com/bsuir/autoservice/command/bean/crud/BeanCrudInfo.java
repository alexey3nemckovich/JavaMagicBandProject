package main.com.bsuir.autoservice.command.bean.crud;

import main.com.bsuir.autoservice.command.CommandDataTypeRequestParameter;

import java.util.Map;

public class BeanCrudInfo {
    @CommandDataTypeRequestParameter
    public String name;
    @CommandDataTypeRequestParameter
    public Map<String, String> fields;
    @CommandDataTypeRequestParameter
    public String action;

    String result;
}
