package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.*;

public class ordered_service extends Bean{
    private int serviceId;
    private int orderId;
    private Date date;

    public int getServiceId(){
        return serviceId;
    }

    public void setServiceId(int value){
        this.serviceId = value;
    }

    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int value){
        this.orderId = value;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date value){
        this.date = value;
    }

    @Override
    public Field[] getFieldsOrdered(){
        return null;
    }

    @Override
    public ordered_service setFields(Map<String, String> fieldValues) {
        return null;
    }
}
