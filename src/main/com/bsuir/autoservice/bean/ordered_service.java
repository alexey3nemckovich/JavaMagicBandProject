package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;

public class ordered_service extends Bean{

    public int getServiceId(){
        return service_id;
    }

    public void setServiceId(int value){
        this.service_id = value;
    }

    public int getOrderId(){
        return order_id;
    }

    public void setOrderId(int value){
        this.order_id = value;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date value){
        this.date = value;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException{
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("service_id"),
                    type.getDeclaredField("order_id"),
                    type.getDeclaredField("date")
            };
            for (Field field: fields) {
                field.setAccessible(true);
            }
            return fields;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    @Override
    public ordered_service setFields(Map<String, String> fieldValues) throws BeanException{
        try {
            service_id = Integer.valueOf(fieldValues.get("service_id"));
            order_id = Integer.valueOf(fieldValues.get("order_id"));
            date = dateFormat.parse(fieldValues.get("date"));
            return this;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    private int service_id;
    private int order_id;
    private Date date;
}
