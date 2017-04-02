package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Discount extends Bean{
    private int id;
    private int serviceId;
    private int value;

    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public int getServiceId(){
        return this.serviceId;
    }

    public void setServiceId(int value){
        this.serviceId = value;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    @Override
    public List<Field> getFieldsOrdered() {
        return null;
    }
}