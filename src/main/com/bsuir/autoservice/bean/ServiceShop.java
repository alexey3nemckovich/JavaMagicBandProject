package main.com.bsuir.autoservice.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceShop extends Bean{
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
    public List<String> getFieldsOrdered(){
        return new ArrayList<String>(Arrays.asList(
                String.valueOf(id), city, street, house, String.valueOf(chiefId)
        ));
    }
}