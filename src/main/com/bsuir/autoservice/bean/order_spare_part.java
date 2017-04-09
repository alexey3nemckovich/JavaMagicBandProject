package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class order_spare_part extends Bean{
    private int sparePartId;
    private int orderId;
    private int count;

    public int getSparePartId(){
        return sparePartId;
    }

    public void setSparePartId(int value){
        this.sparePartId = value;
    }

    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int value){
        this.orderId = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int value){
        this.count = value;
    }

    @Override
    public Field[] getFieldsOrdered(){
        return null;
    }

    @Override
    public order_spare_part setFields(Map<String, String> fieldValues) {
        return null;
    }
}
