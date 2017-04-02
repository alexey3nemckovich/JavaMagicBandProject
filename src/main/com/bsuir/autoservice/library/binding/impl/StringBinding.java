package main.com.bsuir.autoservice.library.binding.impl;

import main.com.bsuir.autoservice.library.binding.AbstractBinding;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;

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
