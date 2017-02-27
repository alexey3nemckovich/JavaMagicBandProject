package main.com.bsuir.autoservice.binder.impl;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import javafx.util.Pair;
import main.com.bsuir.autoservice.binder.IBinder;
import main.com.bsuir.autoservice.binder.exception.BinderException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class DefaultBinder implements IBinder {
    private static final String methodName = "valueOf";

    private Object generateNewObject(Class aClass) throws IllegalAccessException, InstantiationException {
        return aClass.newInstance();
    }

    private Object getMappedObject(Class returnType, Map<String, String[]> parameters, String[] checkedParameters)
            throws InstantiationException, IllegalAccessException {
        Object newObject = generateNewObject(returnType);
        for (String checkedParameter: checkedParameters){
            if (parameters.containsKey(checkedParameter)) {
                updateObject(newObject, checkedParameter, parameters.get(checkedParameter));
            }else{
                //TODO: log unmapped Object
            }
        }
        return newObject;
    }

    private Object getMappedObject(Class returnType, Map<String, String[]> parameters)
            throws InstantiationException, IllegalAccessException {
        Object newObject = generateNewObject(returnType);
        for (Field fieldDeclaration: returnType.getFields()){
            if (parameters.containsKey(fieldDeclaration.getName())) {
                updateObject(newObject, fieldDeclaration, parameters.get(fieldDeclaration.getName()));
            }else{
                updateDefaultObject(newObject, fieldDeclaration);
            }
        }
        return newObject;
    }

    private Object getMappedObject(Class returnType, Map<String, String[]> parameters, Pair<String,String>[] checkedMapParameters)
            throws InstantiationException, IllegalAccessException {
        Object newObject = generateNewObject(returnType);
        for (Pair<String,String> checkedMapParameter: checkedMapParameters){
            if (parameters.containsKey(checkedMapParameter.getKey())) {
                updateObject(newObject, checkedMapParameter.getValue(), parameters.get(checkedMapParameter.getKey()));
            }else{
                //TODO: log unmapped Object
            }
        }
        return newObject;
    }

    private void updateDefaultObject(Object newObject, Field fieldDeclaration){
        try {
            fieldDeclaration.set(newObject, fieldDeclaration.getType().newInstance());
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    private Field getFieldDeclaration(Object newObject, String parameter) throws NoSuchFieldException {
        Class objectClass = newObject.getClass();
        return objectClass.getField(parameter);
    }

    private void updateObject(Object newObject, Field fieldDeclaration, String[] currentValues) {
        try {
            Method convertMethod = fieldDeclaration.getType().getMethod(methodName, String.class);
            //TODO: think can more 1 arguments
            fieldDeclaration.set(newObject, convertMethod.invoke(null, currentValues[0]));
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    private void updateObject(Object newObject, String parameter, String[] currentValues) {
        try {
            Field fieldDeclaration = getFieldDeclaration(newObject,parameter);
            updateObject(newObject,fieldDeclaration,currentValues);
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    @Override
    public Object mappedParameters(Class returnType, Map<String, String[]> parameters, String[] checkedParameters)
            throws BinderException {
        try {
            return getMappedObject(returnType,parameters,checkedParameters);
        }catch (Exception e){
            throw new BinderException(e);
        }
    }

    @Override
    public Object mappedParameters(Class returnType, Map<String, String[]> parameters) throws BinderException {
        try {
            return getMappedObject(returnType,parameters);
        }catch (Exception e){
            throw new BinderException(e);
        }
    }

    @Override
    public Object mappedParameters(Class returnType, Map<String, String[]> parameters, Pair<String, String>[] checkedMapParameters)
            throws BinderException {
        try {
            return getMappedObject(returnType,parameters,checkedMapParameters);
        }catch (Exception e){
            throw new BinderException(e);
        }
    }
}
