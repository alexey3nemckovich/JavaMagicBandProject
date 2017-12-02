package main.com.bsuir.autoservice.bean.impl;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.bean.impl.backup.discount;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class driver extends Bean{

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_driver_status() {
        return id_driver_status;
    }

    public void setId_driver_status(Integer id_driver_status) {
        this.id_driver_status = id_driver_status;
    }

    public Integer getId_staff() {
        return id_staff;
    }

    public void setId_staff(Integer id_staff) {
        this.id_staff = id_staff;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException {
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("id"),
                    type.getDeclaredField("id_driver_status"),
                    type.getDeclaredField("id_staff")
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
    public driver setFields(Map<String, String> fieldValues) {
        id = Integer.valueOf(fieldValues.get("id"));
        id_driver_status = Integer.valueOf(fieldValues.get("id_driver_status"));
        id_staff = Integer.valueOf(fieldValues.get("id_staff"));
        return this;
    }

    private Integer id;
    private Integer id_driver_status;
    private Integer id_staff;
}
