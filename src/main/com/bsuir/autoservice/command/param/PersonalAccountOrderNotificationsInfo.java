package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class PersonalAccountOrderNotificationsInfo implements ICommandParam{
    private int orderId;
    private int pageNumber;
    private int numberPageElements;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public int getOrderId() {
        return orderId;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getNumberPageElements() {
        return numberPageElements;
    }
}
