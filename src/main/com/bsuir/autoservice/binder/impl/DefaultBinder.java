package main.com.bsuir.autoservice.binder.impl;

import main.com.bsuir.autoservice.binder.IBinder;
import main.com.bsuir.autoservice.binder.exception.BinderException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DefaultBinder implements IBinder {

    public DefaultBinder()
    {
        parametersMap = new HashMap<>();
    }

    @Override
    public void addBind(String url, Class<?> aClass) throws BinderException {
        try {
            parametersMap.put(url,aClass);
        }
        catch (Exception e){
            throw new BinderException(e);
        }
    }

    @Override
    public void removeBind(String url) throws BinderException {
        try {
            if (parametersMap.containsKey(url)) {
                parametersMap.remove(url);
            }else {
                //TODO: log that not having key
            }
        }
        catch (Exception e){
            throw new BinderException(e);
        }
    }

    @Override
    public Object mappedParameters(String url, Map<String, String[]> parameters) throws BinderException {
        try {
            Object newObject;
            boolean haveUrl = parametersMap.containsKey(url);
            if (haveUrl){
                newObject = getMappedObject(parametersMap.get(url),parameters);
            }else{
                //TODO: error page
                newObject = null;
            }
            return newObject;
        }catch (Exception e){
            throw new BinderException(e);
        }
    }

    private Object generateNewObject(Class<?> aClass) throws IllegalAccessException, InstantiationException {
        return aClass.newInstance();
    }

    private Object getMappedObject(Class<?> aClass, Map<String, String[]> parameters) throws InstantiationException, IllegalAccessException {
        Object newObject = generateNewObject(aClass);

        for(Map.Entry<String,String[]> currentValues : parameters.entrySet()){
            updateObject(newObject, currentValues);
        }
        return newObject;
    }

    private void updateObject(Object newObject, Map.Entry<String, String[]> currentValues) {
        Class<?> objectClass = newObject.getClass();
        try {
            Field fieldDeclaration = objectClass.getField(currentValues.getKey());
            Method convertMethod = fieldDeclaration.getType().getMethod("valueOf", String.class);
            if (currentValues.getValue().length > 1)
                throw  new IllegalArgumentException("Many arguments");
            fieldDeclaration.set(newObject,convertMethod.invoke(null, currentValues.getValue()[0]));
        }catch (Exception e) {
            //TODO: log that invalid parameters
        }
    }

    private final Map<String, Class<?>> parametersMap;
}
