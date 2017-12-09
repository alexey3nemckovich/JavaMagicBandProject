package main.com.bsuir.autoservice.bean.impl;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class driver_car extends Bean{

    public Integer getId_driver() {
        return id_driver;
    }

    public void setId_driver(Integer id_driver) {
        this.id_driver = id_driver;
    }

    public Integer getId_car() {
        return id_car;
    }

    public void setId_car(Integer id_car) {
        this.id_car = id_car;
    }

    @Override
    public Field[] getFieldsOrdered() throws BeanException {
        try {
            Class type = this.getClass();
            Field[] fields = {
                    type.getDeclaredField("id_driver"),
                    type.getDeclaredField("id_car"),
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
        return new ArrayList<>();
    }

    @Override
    public driver_car setNotAutoGeneratedFields(Map<String, String> fieldValues) {
        id_driver = Integer.valueOf(fieldValues.get("id_driver"));
        id_car = Integer.valueOf(fieldValues.get("id_car"));
        return this;
    }

    private Integer id_driver;
    private Integer id_car;
}
