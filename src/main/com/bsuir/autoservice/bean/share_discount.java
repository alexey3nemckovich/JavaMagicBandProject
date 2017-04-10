package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.Map;

public class share_discount extends Bean{

    public int getShareId(){
        return share_id;
    }

    public void setShareId(int value){
        this.share_id = value;
    }

    public int getDiscountId(){
        return discount_id;
    }

    public void setDiscountId(int value){
        this.discount_id = value;
    }

    @Override
    public Field[] getFieldsOrdered() throws NoSuchFieldException{
        Class type = this.getClass();
        Field[] fields = {
                type.getDeclaredField("share_id"),
                type.getDeclaredField("discount_id")
        };
        for (Field field: fields) {
            field.setAccessible(true);
        }
        return fields;
    }

    @Override
    public share_discount setFields(Map<String, String> fieldValues) {
        share_id = Integer.valueOf(fieldValues.get("share_id"));
        discount_id = Integer.valueOf(fieldValues.get("discount_id"));
        return this;
    }

    private int share_id;
    private int discount_id;
}
