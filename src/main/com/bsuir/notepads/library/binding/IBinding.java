package main.com.bsuir.notepads.library.binding;

import main.com.bsuir.notepads.library.binding.exception.BindingException;

public interface IBinding {
    void bind(Object upgradeObject,String upgradeFieldName, String[] bindingValues) throws BindingException;
    void setDefault(Object upgradeObject, String upgradeFieldName) throws BindingException;
}
