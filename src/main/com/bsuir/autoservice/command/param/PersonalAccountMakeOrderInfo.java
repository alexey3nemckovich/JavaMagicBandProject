package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.List;
import java.util.Map;

public class PersonalAccountMakeOrderInfo implements ICommandParam{
    private List<service> orderServices;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public List<service> getOrderServices() {
        return orderServices;
    }
}
