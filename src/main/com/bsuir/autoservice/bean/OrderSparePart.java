package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderSparePart extends Bean{
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
    public List<Field> getFieldsOrdered(){
        return null;
    }
}