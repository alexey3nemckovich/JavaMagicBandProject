package main.com.bsuir.notepads.bean.impl;

import main.com.bsuir.notepads.bean.Bean;
import main.com.bsuir.notepads.bean.exception.BeanException;

import main.com.bsuir.notepads.library.type.date.SimpleDate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class order extends Bean{

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_customer() {
        return id_customer;
    }

    public void setId_customer(Integer id_customer) {
        this.id_customer = id_customer;
    }

    public Integer getId_driver() {
        return id_driver;
    }

    public void setId_driver(Integer id_driver) {
        this.id_driver = id_driver;
    }

    public Integer getId_address() {
        return id_address;
    }

    public void setId_address(Integer id_address) {
        this.id_address = id_address;
    }

    public Integer getId_order_status() {
        return id_order_status;
    }

    public void setId_order_status(Integer id_order_status) {
        this.id_order_status = id_order_status;
    }

    public Integer getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(Integer total_sum) {
        this.total_sum = total_sum;
    }

    public SimpleDate getDate() {
        return date;
    }

    public void setDate(SimpleDate date) {
        this.date = date;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException {
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("id"),
                    type.getDeclaredField("id_customer"),
                    type.getDeclaredField("id_driver"),
                    type.getDeclaredField("id_address"),
                    type.getDeclaredField("id_order_status"),
                    type.getDeclaredField("total_sum"),
                    type.getDeclaredField("date"),
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
        strings.add("total_sum");
        return strings;
    }

    @Override
    public order setNotAutoGeneratedFields(Map<String, String> fieldValues) throws BeanException{
        try {
            id_customer = Integer.valueOf(fieldValues.get("id_customer"));
            id_driver = Integer.valueOf(fieldValues.get("id_driver"));
            id_address = Integer.valueOf(fieldValues.get("id_address"));
            id_order_status = Integer.valueOf(fieldValues.get("id_order_status"));
            date = new SimpleDate(fieldValues.get("date"));
        }catch (Exception e) {
            throw new BeanException(e);
        }
        return this;
    }

    private Integer id;
    private Integer id_customer;
    private Integer id_driver;
    private Integer id_address;
    private Integer id_order_status;
    private Integer total_sum;
    private SimpleDate date;
}