package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class MechanicChangeOrderStateInfo implements ICommandParam{
    private order.State orderState;
    private int orderId;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public order.State getOrderState() {
        return orderState;
    }

    public int getOrderId() {
        return orderId;
    }
}
