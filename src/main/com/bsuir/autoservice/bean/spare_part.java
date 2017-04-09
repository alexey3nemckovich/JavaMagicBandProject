package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class spare_part extends Bean{
    private int sparePartId;
    private String name;
    private int amountAvailable;

    public int getSparePartId(){
        return sparePartId;
    }

    public void setSparePartId(int value){
        this.sparePartId = value;
    }

    public String getName(){
        return name;
    }

    public void setName(String value){
        this.name = value;
    }

    public int getAmountAvailable(){
        return amountAvailable;
    }

    public void setAmountAvailable(int value){
        this.amountAvailable = value;
    }

    @Override
    public Field[] getFieldsOrdered(){
        return null;
    }

    @Override
    public spare_part setFields(Map<String, String> fieldValues) {
        return null;
    }
}
