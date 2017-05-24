package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class PersonalAccountOrderDetailsInfo implements ICommandParam{
    private int detailOrderId;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        detailOrderId = Integer.valueOf(params.get("orderId")[0]);
        return null;
    }

    public int getDetailOrderId() {
        return detailOrderId;
    }
}
