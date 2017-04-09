package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class share_discount extends Bean{
    private int shareId;
    private int discountId;

    public int getShareId(){
        return shareId;
    }

    public void setShareId(int value){
        this.shareId = value;
    }

    public int getDiscountId(){
        return discountId;
    }

    public void setDiscountId(int value){
        this.discountId = value;
    }

    @Override
    public Field[] getFieldsOrdered(){
        return null;
    }

    @Override
    public share_discount setFields(Map<String, String> fieldValues) {
        return null;
    }
}
