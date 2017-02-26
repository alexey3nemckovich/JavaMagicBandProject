package main.com.bsuir.autoservice.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparePart extends Bean{
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
    public List<String> getFieldsOrdered(){
        return new ArrayList<String>(Arrays.asList(
                String.valueOf(sparePartId), name, String.valueOf(amountAvailable)
        ));
    }
}