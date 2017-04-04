package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Staff extends Bean{
    public enum Specialization{
        MECHANIC, CHIEF_MECHANIC, ADMIN, DIRECTOR
    }

    private int id;
    private int serviceShopId;
    private int userId;
    private Specialization specialization;

    public int getId(){
        return id;
    }

    public void setId(int value){
        this.id = value;
    }

    public int getServiceShopId(){
        return serviceShopId;
    }

    public void setServiceShopId(int value){
        this.serviceShopId = value;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int value){
        this.userId = value;
    }

    public Specialization getSpecialization(){
        return specialization;
    }

    public void setSpecialization(Specialization specialization){
        this.specialization = specialization;
    }

    @Override
    public List<Field> getFieldsOrdered(){
        try {
            Class type = this.getClass();
            List<Field> fields = new ArrayList<Field>(Arrays.asList(
                    type.getDeclaredField("id"),
                    type.getDeclaredField("serviceShopId"),
                    type.getDeclaredField("userId"),
                    type.getDeclaredField("specialization")
            ));
            for (Field field: fields) {
                field.setAccessible(true);
            }
            return fields;
        }catch (Exception e){
            //impossible
            return null;
        }
    }
}
