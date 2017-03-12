package main.com.bsuir.autoservice.library.mapper.binding.impl;

import main.com.bsuir.autoservice.library.mapper.binding.AbstractBinding;
import main.com.bsuir.autoservice.library.mapper.binding.exception.BindingException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;

public class IntegerBinding extends AbstractBinding{
    private static final String bindingMethodName = "valueOf";
    private static final Class[] bindingMethodParameters = new Class[] {String.class};
    private static final int defaultIntegerValue = 0;


    @Override
    protected String getBindingMethodName() {
        return bindingMethodName;
    }

    @Override
    protected Class[] getBindingMethodParameters() {
        return bindingMethodParameters;
    }

    protected void upgradeDefaultField(Object upgradeObject, Field fieldDeclaration) throws Exception {
        fieldDeclaration.set(upgradeObject, new Integer(defaultIntegerValue));
    }
}
