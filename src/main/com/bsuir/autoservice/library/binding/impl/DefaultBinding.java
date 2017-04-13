package main.com.bsuir.autoservice.library.binding.impl;

import main.com.bsuir.autoservice.library.binding.AbstractBinding;

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
