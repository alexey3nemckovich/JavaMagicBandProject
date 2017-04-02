package main.com.bsuir.autoservice.http.parser;

import com.google.common.base.Defaults;
import main.com.bsuir.autoservice.http.parser.exception.HttpParserException;

import java.lang.reflect.Field;
import java.util.Map;

public class DefaultHttpParser implements IHttpParser {

    @Override
    public <T> T parseParameters(Class<T> parseObjectType, Map<String, String[]> parameters)
            throws HttpParserException{
        try {
            return parseObject(parseObjectType, parameters);
        }catch (Exception e){
            throw new HttpParserException(e);
        }
    }

    private <T> T parseObject(Class<T> returnType, Map<String, String[]> parameters)
            throws Exception{
        T parsedObject = returnType.newInstance();
        for (Field field: returnType.getFields()){
            String fieldName = field.getName();
            if (parameters.containsKey(fieldName)) {
                setFieldValue(parsedObject, field, parameters.get(fieldName)[0]);
            }else{
                setFieldDefaultValue(parsedObject, field);
            }
        }
        return parsedObject;
    }

    private <T> void setFieldValue(T object, Field field, String value)
            throws Exception{
        field.set(object, getFieldValueObject(field, value));
    }

    private <T> void setFieldDefaultValue(T object, Field field)
            throws IllegalAccessException{
        field.set(object, Defaults.defaultValue(field.getClass()));
    }

    @SuppressWarnings("unchecked")
    private Object getFieldValueObject(Field field, String value)
            throws Exception{
        Class fieldClass = field.getClass();
        Object fieldValueObject = fieldClass.newInstance();
        fieldValueObject = fieldClass.getMethod("valueOf").invoke(fieldValueObject, value);
        return fieldValueObject;
    }
}
