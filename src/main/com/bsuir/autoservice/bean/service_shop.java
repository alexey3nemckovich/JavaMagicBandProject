package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class service_shop extends Bean{
    private int id;
    private String city;
    private String street;
    private String house;
    private int chiefId;

    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String value){
        this.city = value;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String value){
        this.street = value;
    }

    public String getHouse(){
        return house;
    }

    public void setHouse(String value){
        this.house = value;
    }

    public int getChiefId(){
        return chiefId;
    }

    public void setChiefId(int value){
        this.chiefId = value;
    }

    @Override
    public Field[] getFieldsOrdered(){
        return null;
    }

    @Override
    public service_shop setFields(Map<String, String> fieldValues) {
        return null;
    }
}
