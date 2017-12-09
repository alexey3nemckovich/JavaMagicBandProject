package main.com.bsuir.notepads.library.binding.factory;

import main.com.bsuir.notepads.library.binding.IBinding;
import main.com.bsuir.notepads.library.binding.factory.exception.BindingFactoryException;

public interface IBindingFactory {
    void addBinding(Class bindingClass, IBinding binding) throws BindingFactoryException;
    void removeBinding(Class bindingClass) throws BindingFactoryException;
    IBinding getBinding(Class bindingClass) throws BindingFactoryException;
}
