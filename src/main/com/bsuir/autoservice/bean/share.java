package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;

public class share extends Bean{
    public enum State{
        ACTIVE, EXPIRED
    }

    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public Date getDateStart(){
        return date_start;
    }

    public void setDateStart(Date value){
        this.date_start = value;
    }

    public Date getDateEnd(){
        return date_end;
    }

    public void setDateEnd(Date value){
        this.date_end = value;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String value){
        this.description = value;
    }

    public State getState(){
        return state;
    }

    public void setState(State value){
        this.state = value;
    }

    @Override
    public Field[] getFieldsOrdered() throws NoSuchFieldException{
        Class type = this.getClass();
        Field[] fields = {
                type.getDeclaredField("id"),
                type.getDeclaredField("date_start"),
                type.getDeclaredField("date_end"),
                type.getDeclaredField("value"),
                type.getDeclaredField("description"),
                type.getDeclaredField("state")
        };
        for (Field field: fields) {
            field.setAccessible(true);
        }
        return fields;
    }

    @Override
    public share setFields(Map<String, String> fieldValues) throws ParseException{
        id = Integer.valueOf(fieldValues.get("id"));
        date_start = dateFormat.parse(fieldValues.get("date_start"));
        date_end = dateFormat.parse(fieldValues.get("date_end"));
        value = Integer.valueOf(fieldValues.get("value"));
        description = fieldValues.get("description");
        state = State.valueOf(fieldValues.get("state"));
        return this;
    }

    private int id;
    private Date date_start;
    private Date date_end;
    private int value;
    private String description;
    private State state;
}
