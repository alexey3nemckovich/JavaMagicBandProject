package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscountUser extends Bean{
    private int discountId;
    private int userId;

    public int getDiscountId(){
        return discountId;
    }

    public void setDiscountId(int value){
        this.discountId = value;
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int value){
        this.userId = value;
    }

    @Override
    public List<Field> getFieldsOrdered(){
        return null;
    }
}