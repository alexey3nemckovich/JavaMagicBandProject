package main.com.bsuir.autoservice.bean;

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
    public List<String> getFieldsOrdered() {
        return new ArrayList<String>(Arrays.asList(
                String.valueOf(id), String.valueOf(serviceId), String.valueOf(value)
        ));
    }
}