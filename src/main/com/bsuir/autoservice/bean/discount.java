package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.Map;

public class discount extends Bean{

    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public int getServiceId(){
        return this.service_id;
    }

    public void setServiceId(int value){
        this.service_id = value;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException{
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("id"),
                    type.getDeclaredField("service_id"),
                    type.getDeclaredField("value")
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
    public discount setFields(Map<String, String> fieldValues) {
        id = Integer.valueOf(fieldValues.get("id"));
        service_id = Integer.valueOf(fieldValues.get("service_id"));
        value = Integer.valueOf(fieldValues.get("value"));
        return this;
    }

    private int id;
    private int service_id;
    private int value;
}