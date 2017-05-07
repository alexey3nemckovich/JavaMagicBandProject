package main.com.bsuir.autoservice.bean.impl;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;

import java.lang.reflect.Field;
import java.util.*;

public class notification extends Bean {
    public enum State{
        CONFIRMED, UNCONFIRMED
    }

    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public int getOrderId(){
        return order_id;
    }

    public void setOrderId(int value){
        this.order_id = value;
    }

    public int getStaffId(){
        return staff_id;
    }

    public void setStaffId(int value){
        this.staff_id = value;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date value){
        this.date = value;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String value){
        this.content = value;
    }

    public State getState(){
        return state;
    }

    public void setState(State value){
        this.state = value;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException {
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("id"),
                    type.getDeclaredField("order_id"),
                    type.getDeclaredField("staff_id"),
                    type.getDeclaredField("date"),
                    type.getDeclaredField("content"),
                    type.getDeclaredField("state")
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
    public notification setFields(Map<String, String> fieldValues) throws BeanException{
        try {
            id = Integer.valueOf(fieldValues.get("discount_id"));
            order_id = Integer.valueOf(fieldValues.get("order_id"));
            staff_id = Integer.valueOf(fieldValues.get("staff_id"));
            date = tryParseDate(fieldValues.get("date"));
            content = fieldValues.get("content");
            state = State.valueOf(fieldValues.get("state"));
            return this;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    private int id;
    private int order_id;
    private int staff_id;
    private Date date;
    private String content;
    private State state;
}
