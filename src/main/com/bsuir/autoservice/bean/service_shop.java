package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.Map;

public class service_shop extends Bean{

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
        return chief_id;
    }

    public void setChiefId(int value){
        this.chief_id = value;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException{
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("id"),
                    type.getDeclaredField("city"),
                    type.getDeclaredField("street"),
                    type.getDeclaredField("house"),
                    type.getDeclaredField("chief_id")
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
    public service_shop setFields(Map<String, String> fieldValues) {
        id = Integer.valueOf(fieldValues.get("id"));
        city = fieldValues.get("city");
        street = fieldValues.get("street");
        house = fieldValues.get("house");
        chief_id = Integer.valueOf(fieldValues.get("chief_id"));
        return this;
    }

    private int id;
    private String city;
    private String street;
    private String house;
    private int chief_id;
}
