package main.com.bsuir.autoservice.http.parser;

import com.google.common.base.Defaults;
import com.google.common.collect.ImmutableMap;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.command.CommandDataTypeRequestParameter;
import main.com.bsuir.autoservice.http.parser.exception.HttpParserException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
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
        boolean fullRequestObject = returnType.isAnnotationPresent(CommandDataTypeRequestParameter.class);
        for (Field field: returnType.getDeclaredFields()){
            String fieldName = field.getName();
            if ((fullRequestObject || field.isAnnotationPresent(CommandDataTypeRequestParameter.class)) &&
                    parameters.containsKey(fieldName)) {
                setFieldValue(parsedObject, field, parameters.get(fieldName)[0]);
            }else{
                setFieldDefaultValue(parsedObject, field);
            }
        }
        return parsedObject;
    }

    private <T> void setFieldValue(T object, Field field, String value)
            throws Exception{
        field.setAccessible(true);
        field.set(object, getFieldValueObject(field, value));
    }

    private <T> void setFieldDefaultValue(T object, Field field)
            throws IllegalAccessException, NoSuchFieldException{
        Object defaultValue;
        if(field.getType().getName().contains("String")){
            defaultValue = "";
        }else {
            defaultValue = Defaults.defaultValue(field.getType());
        }
        field.setAccessible(true);
        field.set(object, defaultValue);
    }

    @SuppressWarnings("unchecked")
    private Object getFieldValueObject(Field field, String value)
            throws Exception{
        if(field.getType().getName().contains("String")){
            return value;
        }else{
            Class fieldType = field.getType();
            if(fieldType.isPrimitive()){
                fieldType = PRIMITIVES_TO_WRAPPERS.get(fieldType);
            }
            Method parseMethod = fieldType.getMethod("valueOf", new Class[]{String.class});
            return parseMethod.invoke(fieldType, value);
        }
    }

    private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS
            = new ImmutableMap.Builder<Class<?>, Class<?>>()
            .put(boolean.class, Boolean.class)
            .put(byte.class, Byte.class)
            .put(char.class, Character.class)
            .put(double.class, Double.class)
            .put(float.class, Float.class)
            .put(int.class, Integer.class)
            .put(long.class, Long.class)
            .put(short.class, Short.class)
            .put(void.class, Void.class)
            .build();
}
