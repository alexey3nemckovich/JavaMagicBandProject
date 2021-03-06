package main.com.bsuir.autoservice.bean.impl;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;

import javax.lang.model.type.NullType;
import java.lang.reflect.Field;
import java.util.*;

public class OrderedService extends Bean<NullType> {

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
    public Field[] getFieldsOrdered() throws BeanException {
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

    public List<String> getAutoGeneratedFields() throws BeanException{
        List<String> strings = new ArrayList<>();
        return strings;
    }

    @Override
    public OrderedService setFields(Map<String, String> fieldValues) throws BeanException{
        try {
            service_id = Integer.valueOf(fieldValues.get("service_id"));
            order_id = Integer.valueOf(fieldValues.get("order_id"));
            date = tryParseDate(fieldValues.get("date"));
            return this;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    @Override
    public NullType getId() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedService that = (OrderedService) o;
        return Objects.equals(service_id, that.service_id) &&
                Objects.equals(order_id, that.order_id) &&
                Objects.equals(date, that.date);
    }

    @Override
    public Field[] getRenderFields() throws BeanException{
        return getFieldsOrdered();
    }

    @Override
    public int hashCode() {
        return Objects.hash(service_id, order_id, date);
    }

    private Integer service_id;
    private Integer order_id;
    private Date date;
}
