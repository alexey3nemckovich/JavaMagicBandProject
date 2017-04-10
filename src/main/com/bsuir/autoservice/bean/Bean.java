package main.com.bsuir.autoservice.bean;

import javax.swing.text.DateFormatter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Bean {

    public abstract Field[] getFieldsOrdered() throws NoSuchFieldException;
    public abstract Bean setFields(Map<String, String> fieldValues) throws ParseException;

    @SuppressWarnings("unchecked")
    public static Bean getBeanObject(String beanName, Map<String, String> fields)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, ParseException{
        Class<? extends Bean> beanClass =
                (Class<? extends Bean>)Class.forName(
                        Bean.class.getPackage().getName() + '.' + beanName
                );
        Bean bean = beanClass.newInstance();
        bean.setFields(fields);
        return bean;
    }

    public LinkedHashMap<String, String> getFieldValues()
            throws IllegalAccessException, NoSuchFieldException{
        LinkedHashMap<String, String> fieldValues = new LinkedHashMap<>();
        Field[] fields = getFieldsOrdered();
        Object fieldValue;
        for (Field field: fields) {
            fieldValue = field.get(this);
            if(null != fieldValue){
                fieldValues.put(field.getName(), fieldValue.toString());
            }else{
                fieldValues.put(field.getName(), "");
            }
        }
        return fieldValues;
    }

    protected static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
}
