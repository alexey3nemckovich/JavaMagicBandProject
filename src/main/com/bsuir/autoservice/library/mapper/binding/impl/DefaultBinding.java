package main.com.bsuir.autoservice.library.mapper.binding.impl;

import main.com.bsuir.autoservice.library.mapper.binding.AbstractBinding;
import main.com.bsuir.autoservice.library.mapper.binding.IBinding;
import main.com.bsuir.autoservice.library.mapper.binding.exception.BindingException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DefaultBinding extends AbstractBinding {
    private static final String bindingMethodName = "valueOf";
    private static final Class[] bindingMethodParameters = new Class[] {String.class};

    @Override
    protected String getBindingMethodName() {
        return bindingMethodName;
    }

    @Override
    protected Class[] getBindingMethodParameters() {
        return bindingMethodParameters;
    }
}
