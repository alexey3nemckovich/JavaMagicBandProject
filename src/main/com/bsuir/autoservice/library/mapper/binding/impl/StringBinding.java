package main.com.bsuir.autoservice.library.mapper.binding.impl;

import main.com.bsuir.autoservice.library.mapper.binding.AbstractBinding;
import main.com.bsuir.autoservice.library.mapper.binding.IBinding;
import main.com.bsuir.autoservice.library.mapper.binding.exception.BindingException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StringBinding extends AbstractBinding {

    @Override
    protected String getBindingMethodName() {
        throw new NotImplementedException();
    }

    @Override
    protected Class[] getBindingMethodParameters() {
        throw new NotImplementedException();
    }

    @Override
    protected void upgradeField(Object upgradeObject, Field fieldDeclaration, String[] bindingValues) throws Exception{
        fieldDeclaration.set(upgradeObject, getInvokedParameters(bindingValues));
    }
}
