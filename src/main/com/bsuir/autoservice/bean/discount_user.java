package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class discount_user extends Bean{
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
    public Field[] getFieldsOrdered(){
        return null;
    }

    @Override
    public discount_user setFields(Map<String, String> fieldValues) {
        return null;
    }
}
