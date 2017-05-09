package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class MechanicAddOrderNotificationInfo implements ICommandParam{
    private int orderId;
    private String notificationMessage;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public int getOrderId() {
        return orderId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }
}
