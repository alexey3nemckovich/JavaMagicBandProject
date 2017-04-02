package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShareDiscount extends Bean{
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
    public List<Field> getFieldsOrdered(){
        return null;
    }
}