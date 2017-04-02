package main.com.bsuir.autoservice.library.binding;

import main.com.bsuir.autoservice.library.binding.exception.BindingException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class AbstractBinding implements IBinding {
    //TODO: extend as component method (in other class)
    protected abstract String getBindingMethodName();
    protected abstract Class[] getBindingMethodParameters();
    //

    protected Object getInvokedObject(){
        return null;
    }

    protected Object getInvokedParameters(String[] bindingValues){
        //TODO: think can more 1 arguments
        return bindingValues[0];
    }

    protected void upgradeField(Object upgradeObject, Field fieldDeclaration, String[] bindingValues) throws Exception{
        Method convertMethod = fieldDeclaration.getType().getMethod(getBindingMethodName(), getBindingMethodParameters());
        fieldDeclaration.set(upgradeObject, convertMethod.invoke(getInvokedObject(), getInvokedParameters(bindingValues)));
    }

    protected void upgradeDefaultField(Object upgradeObject, Field fieldDeclaration) throws Exception {
        fieldDeclaration.set(upgradeObject, fieldDeclaration.getType().newInstance());
    }

    @Override
    public void bind(Object upgradeObject, String upgradeFieldName, String[] bindingValues) throws BindingException {
        try {
            Field fieldDeclaration = getFieldDeclaration(upgradeObject, upgradeFieldName);
            upgradeField(upgradeObject, fieldDeclaration, bindingValues);
        }catch (Exception e){
            //TODO: log that invalid parameters
            setDefault(upgradeObject, upgradeFieldName);
        }
    }

    private static Field getFieldDeclaration(Object newObject, String parameter) throws NoSuchFieldException {
        Class objectClass = newObject.getClass();
        return objectClass.getField(parameter);
    }

    @Override
    public void setDefault(Object upgradeObject, String upgradeFieldName) throws BindingException {
        try {
            Field fieldDeclaration = getFieldDeclaration(upgradeObject, upgradeFieldName);
            upgradeDefaultField(upgradeObject, fieldDeclaration);
        }catch (Exception e) {
            //TODO: log that invalid parameters
            throw new BindingException(e);
        }
    }
}
