package main.com.bsuir.autoservice.bean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Bean {
    abstract List<String> getFieldsOrdered();

    public Map<String, String> getAllFields() throws BeanException{
        Map<String, String> fieldsStringValues = new HashMap<String, String>();
        Field[] fields = Bean.class.getFields();
        for (Field field: fields) {
            field.setAccessible(true);
            try {
                fieldsStringValues.put(field.getName(), field.get(this).toString());
            }catch (IllegalAccessException e){
                throw new BeanException(e);
            }
        }
        return fieldsStringValues;
    }

    public String getField(String fieldName) throws BeanException{
        try {
            return this.getClass().getField(fieldName).toString();
        }catch (NoSuchFieldException e){
            throw new BeanException(e);
        }
    }

    public void setField(String fieldName, Object value) throws BeanException{
        try {
            Field field = this.getClass().getField(fieldName);
            field.setAccessible(true);
            field.set(this, value);
        }catch (NoSuchFieldException e){
            throw new BeanException(e);
        }
        catch (IllegalAccessException e){
            throw new BeanException(e);
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');
        List<String> fields = getFieldsOrdered();
        for(int i = 0; i < fields.size(); i++){
            stringBuilder.append(fields.get(i));
            if (fields.size() - 1 != i){
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}