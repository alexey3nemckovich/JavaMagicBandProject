package main.com.bsuir.autoservice.bean;

import main.com.bsuir.autoservice.bean.exception.BeanException;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Bean<PrimaryKey> {

    public abstract Field[] getRenderFields() throws BeanException;
    public abstract List<String> getAutoGeneratedFields() throws BeanException;
    public abstract Field[] getFieldsOrdered() throws BeanException;
    public abstract Bean setFields(Map<String, String> fieldValues) throws BeanException;
    public abstract PrimaryKey getId();

    public static Bean getBeanObject(Class<? extends Bean> beanClass, Map<String, String> fields) throws BeanException{
        try {
            Bean bean = beanClass.newInstance();
            bean.setFields(fields);
            return bean;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    public static Bean getBeanObject(Class<? extends Bean> beanClass) throws BeanException{
        try {
            return beanClass.newInstance();
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    @Override
    public String toString(){
        try {
            Field[] fields = getFieldsOrdered();
            StringBuilder stringBuilder = new StringBuilder("{");
            for(int i = 0; i < fields.length - 2; i++){
                stringBuilder.append(fields[i].getName());
                stringBuilder.append("=");
                stringBuilder.append(fields[i].get(this).toString());
                stringBuilder.append(", ");
            }
            stringBuilder.append(fields[fields.length-1].getName());
            stringBuilder.append("=");
            stringBuilder.append(fields[fields.length-1].get(this).toString());
            stringBuilder.append("}");
            return stringBuilder.toString();
        }catch (Exception e){
            return "";
        }
    }

    public Map<String, String>  getFieldValuesStrings() throws BeanException{
        try {
            Map<String, String> fieldValues = new HashMap<>();
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
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    public Map<String, Object> getFieldValues() throws BeanException{
        try {
            Map<String, Object> fieldValues = new HashMap<>();
            Field[] fields = getFieldsOrdered();
            Object fieldValue;
            for (Field field: fields) {
                fieldValue = field.get(this);
                fieldValues.put(field.getName(), fieldValue);
            }
            return fieldValues;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    public static Date tryParseDate(String dateStr) throws ParseException{
        return dateFormat.parse(dateStr);
    }

    protected static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
}
