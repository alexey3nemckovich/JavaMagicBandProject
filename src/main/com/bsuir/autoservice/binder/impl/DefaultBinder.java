package main.com.bsuir.autoservice.binder.impl;

import main.com.bsuir.autoservice.binder.IBinder;
import main.com.bsuir.autoservice.binder.exception.BinderException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class DefaultBinder implements IBinder {
    private Object generateNewObject(Class<?> aClass) throws IllegalAccessException, InstantiationException {
        return aClass.newInstance();
    }

    private Object getMappedObject(String[] checkedParameters, Map<String, String[]> parameters, Class returnType) throws InstantiationException, IllegalAccessException {
        Object newObject = generateNewObject(returnType);
        for (String checkedParameter: checkedParameters){
            if (parameters.containsKey(checkedParameter)) {
                updateObject(newObject, checkedParameter, parameters.get(checkedParameter));
            }else{
                updateDefaultObject(newObject, checkedParameter);
            }
        }
        return newObject;
    }

    private void updateDefaultObject(Object newObject, String parameter){
        try {
            Field fieldDeclaration = getFieldDeclaration(newObject,parameter);
            fieldDeclaration.set(newObject, fieldDeclaration.getType().newInstance());
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    private Field getFieldDeclaration(Object newObject, String parameter) throws NoSuchFieldException {
        Class<?> objectClass = newObject.getClass();
        return objectClass.getField(parameter);
    }

    private void updateObject(Object newObject, String parameter, String[] currentValues) {
        try {
            Field fieldDeclaration = getFieldDeclaration(newObject,parameter);
            Method convertMethod = fieldDeclaration.getType().getMethod("valueOf", String.class);
            //TODO: think can more 1 arguments
            fieldDeclaration.set(newObject, convertMethod.invoke(null, currentValues[0]));
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    @Override
    public Object mappedParameters(Class returnType, String[] checkedParameters, Map<String, String[]> parameters) throws BinderException {
        try {
            return getMappedObject(checkedParameters,parameters,returnType);
        }catch (Exception e){
            throw new BinderException(e);
        }
    }
}
