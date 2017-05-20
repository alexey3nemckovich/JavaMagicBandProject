package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class MechanicViewOrdersInfo implements ICommandParam{
    // state - first uncompleted
    public enum SortedType{
        STATE, TIME
    }

    private SortedType orderSortType;
    private int orderPage;
    private int orderCount;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public SortedType getOrderSortType() {
        return orderSortType;
    }

    public int getOrderPage() {
        return orderPage;
    }

    public int getOrderCount() {
        return orderCount;
    }
}
