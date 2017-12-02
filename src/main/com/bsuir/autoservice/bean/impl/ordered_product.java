package main.com.bsuir.autoservice.bean.impl;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.bean.impl.backup.discount;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ordered_product extends Bean{

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public Integer getId_shop_product() {
        return id_shop_product;
    }

    public void setId_shop_product(Integer id_shop_product) {
        this.id_shop_product = id_shop_product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount_cost() {
        return count_cost;
    }

    public void setCount_cost(Integer count_cost) {
        this.count_cost = count_cost;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException {
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("id"),
                    type.getDeclaredField("id_order"),
                    type.getDeclaredField("id_product"),
                    type.getDeclaredField("id_shop_product"),
                    type.getDeclaredField("count"),
                    type.getDeclaredField("count_cost"),
            };
            for (Field field: fields) {
                field.setAccessible(true);
            }
            return fields;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    public List<String> getAutoGeneratedFields() throws BeanException{
        List<String> strings = new ArrayList<>();
        strings.add("id");
        strings.add("count_cost");
        return strings;
    }

    @Override
    public ordered_product setFields(Map<String, String> fieldValues) {
        id = Integer.valueOf(fieldValues.get("id"));
        id_order = Integer.valueOf(fieldValues.get("id_order"));
        id_product = Integer.valueOf(fieldValues.get("id_product"));
        id_shop_product = Integer.valueOf(fieldValues.get("id_shop_product"));
        count = Integer.valueOf(fieldValues.get("count"));
        count_cost = Integer.valueOf(fieldValues.get("count_cost"));
        return this;
    }

    private Integer id;
    private Integer id_order;
    private Integer id_product;
    private Integer id_shop_product;
    private Integer count;
    private Integer count_cost;
}
