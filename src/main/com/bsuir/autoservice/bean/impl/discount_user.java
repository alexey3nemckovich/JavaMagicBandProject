package main.com.bsuir.autoservice.bean.impl;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;

import java.lang.reflect.Field;
import java.util.Map;

public class discount_user extends Bean {

    public int getDiscountId(){
        return discount_id;
    }

    public void setDiscount_id(int value){
        this.discount_id = value;
    }

    public int getUserId(){
        return user_id;
    }

    public void setUserId(int value){
        this.user_id = value;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException {
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("discount_id"),
                    type.getDeclaredField("user_id")
            };
            for (Field field: fields) {
                field.setAccessible(true);
            }
            return fields;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    @Override
    public discount_user setFields(Map<String, String> fieldValues) {
        discount_id = Integer.valueOf(fieldValues.get("discount_id"));
        user_id = Integer.valueOf(fieldValues.get("user_id"));
        return this;
    }

    private int discount_id;
    private int user_id;
}
