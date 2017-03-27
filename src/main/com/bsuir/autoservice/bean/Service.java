package main.com.bsuir.autoservice.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service extends Bean{
    private int id;
    private String name;
    private int cost;

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
    public List<String> getFieldsOrdered(){
        return new ArrayList<String>(Arrays.asList(
                String.valueOf(id), name, String.valueOf(cost)
        ));
    }
}