package main.com.bsuir.autoservice.library.binding;

import main.com.bsuir.autoservice.library.binding.exception.BindingException;

public interface IBinding {
    void bind(Object upgradeObject,String upgradeFieldName, String[] bindingValues) throws BindingException;
    void setDefault(Object upgradeObject, String upgradeFieldName) throws BindingException;
}
