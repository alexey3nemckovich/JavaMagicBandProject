package main.com.bsuir.autoservice.library.mapper.binding.factory;

import main.com.bsuir.autoservice.library.mapper.binding.IBinding;
import main.com.bsuir.autoservice.library.mapper.binding.factory.exception.BindingFactoryException;

import java.lang.reflect.Field;

public interface IBindingFactory {
    void addBinding(Class bindingClass, IBinding binding) throws BindingFactoryException;
    void removeBinding(Class bindingClass) throws BindingFactoryException;
    IBinding getBinding(Class bindingClass) throws BindingFactoryException;
}
