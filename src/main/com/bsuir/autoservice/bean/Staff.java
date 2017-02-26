package main.com.bsuir.autoservice.bean;

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

    @Override
    public List<String> getFieldsOrdered(){
        return new ArrayList<String>(Arrays.asList(
                String.valueOf(id), String.valueOf(serviceShopId), String.valueOf(userId)
        ));
    }
}