package main.com.bsuir.autoservice.bean;

import main.com.bsuir.autoservice.bean.exception.BeanException;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public abstract class Bean {

    public abstract Field[] getFieldsOrdered() throws BeanException;
    public abstract Bean setFields(Map<String, String> fieldValues) throws BeanException;

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

    @SuppressWarnings("unchecked")
    public static Bean getBeanObject(String beanName, Map<String, String> fields) throws BeanException{
        try {
            Class<? extends Bean> beanClass =
                    (Class<? extends Bean>)Class.forName(
                            Bean.class.getPackage().getName() + ".impl." + beanName
                    );
            Bean bean = beanClass.newInstance();
            bean.setFields(fields);
            return bean;
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    public LinkedHashMap<String, String> getFieldValues() throws BeanException{
        try {
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
        }catch (Exception e){
            throw new BeanException(e);
        }
    }

    public static Date tryParseDate(String dateStr) throws ParseException{
        return dateFormat.parse(dateStr);
    }

    protected static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
}
