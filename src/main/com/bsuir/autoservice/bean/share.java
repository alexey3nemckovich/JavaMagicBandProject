package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.*;

public class share extends Bean{
    public enum State{
        ACTIVE, EXPIRED
    }

    private int id;
    private Date dateStart;
    private Date dateEnd;
    private int value;
    private String description;
    private State state;

    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public Date getDateStart(){
        return dateStart;
    }

    public void setDateStart(Date value){
        this.dateStart = value;
    }

    public Date getDateEnd(){
        return dateEnd;
    }

    public void setDateEnd(Date value){
        this.dateEnd = value;
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
    public Field[] getFieldsOrdered(){
        return null;
    }

    @Override
    public share setFields(Map<String, String> fieldValues) {
        return null;
    }
}
