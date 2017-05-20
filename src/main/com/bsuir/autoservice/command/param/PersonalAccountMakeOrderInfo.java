package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.List;
import java.util.Map;

public class PersonalAccountMakeOrderInfo implements ICommandParam{
    private List<Service> orderServices;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public List<Service> getOrderServices() {
        return orderServices;
    }
}
