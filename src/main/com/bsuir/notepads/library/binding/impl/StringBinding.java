package main.com.bsuir.notepads.library.binding.impl;

import main.com.bsuir.notepads.library.binding.AbstractBinding;

import java.lang.UnsupportedOperationException;
import java.lang.reflect.Field;

public class StringBinding extends AbstractBinding {

    @Override
    protected String getBindingMethodName() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Class[] getBindingMethodParameters() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void upgradeField(Object upgradeObject, Field fieldDeclaration, String[] bindingValues) throws Exception{
        fieldDeclaration.set(upgradeObject, getInvokedParameters(bindingValues));
    }
}
