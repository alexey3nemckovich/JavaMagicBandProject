package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class service extends Bean{

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
    public Field[] getFieldsOrdered() throws NoSuchFieldException{
        Class type = this.getClass();
        Field[] fields = {
                type.getDeclaredField("id"),
                type.getDeclaredField("name"),
                type.getDeclaredField("cost")
        };
        for (Field field: fields) {
            field.setAccessible(true);
        }
        return fields;
    }

    @Override
    public service setFields(Map<String, String> fieldValues) {
        id = Integer.valueOf(fieldValues.get("id"));
        name = fieldValues.get("name");
        cost = Integer.valueOf(fieldValues.get("cost"));
        return this;
    }

    private int id;
    private String name;
    private int cost;
}
