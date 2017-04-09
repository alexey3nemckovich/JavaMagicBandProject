package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class service extends Bean{
    private int id;
    private String name;
    private int cost;

    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public String getName(){
        return name;
    }

    public void setName(String value){
        this.name = value;
    }

    public  int getCost(){
        return cost;
    }

    public void setCost(int value){
        this.cost = value;
    }

    @Override
    public Field[] getFieldsOrdered(){
        return null;
    }

    @Override
    public service setFields(Map<String, String> fieldValues) {
        return null;
    }
}
