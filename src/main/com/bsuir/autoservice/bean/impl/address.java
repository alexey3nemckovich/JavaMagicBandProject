package main.com.bsuir.autoservice.bean.impl;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.bean.impl.backup.discount;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class address extends Bean{

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_city() {
        return id_city;
    }

    public void setId_city(Integer id_city) {
        this.id_city = id_city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException {
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("id"),
                    type.getDeclaredField("id_city"),
                    type.getDeclaredField("street"),
                    type.getDeclaredField("postcode"),
                    type.getDeclaredField("house"),
                    type.getDeclaredField("apartment")
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
        return strings;
    }

    @Override
    public address setFields(Map<String, String> fieldValues) {
        id = Integer.valueOf(fieldValues.get("id"));
        id_city = Integer.valueOf(fieldValues.get("id_city"));
        street = fieldValues.get("street");
        postcode = Integer.valueOf(fieldValues.get("postcode"));
        house = Integer.valueOf(fieldValues.get("house"));
        apartment = Integer.valueOf(fieldValues.get("apartment"));
        return this;
    }

    private Integer id;
    private Integer id_city;
    private String street;
    private Integer postcode;
    private Integer house;
    private Integer apartment;
}
