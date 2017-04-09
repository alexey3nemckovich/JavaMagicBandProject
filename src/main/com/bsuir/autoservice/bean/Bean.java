package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Bean {
    public abstract Field[] getFieldsOrdered();
    public abstract Bean setFields(Map<String, String> fieldValues);

    public static Bean getBeanObject(String beanName, Map<String, String> fields)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException{
        Class<? extends Bean> beanClass = (Class<? extends Bean>)Class.forName(Bean.class.getPackage().getName() + '.' + beanName);
        Bean bean = beanClass.newInstance();
        bean.setFields(fields);
        return bean;
    }

    public LinkedHashMap<String, String> getFieldValues() throws IllegalAccessException{
        LinkedHashMap<String, String> fieldValues = new LinkedHashMap<>();
        Field[] fields = getFieldsOrdered();
        Object fieldValue = null;
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

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        Field[] fields = getFieldsOrdered();
        for(int i = 0; i < fields.length; i++){
            stringBuilder.append(fields[i]);
            if (fields.length - 1 != i){
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
