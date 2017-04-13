package main.com.bsuir.autoservice.library.binding.factory;

import main.com.bsuir.autoservice.library.binding.IBinding;
import main.com.bsuir.autoservice.library.binding.factory.exception.BindingFactoryException;

public interface IBindingFactory {
    void addBinding(Class bindingClass, IBinding binding) throws BindingFactoryException;
    void removeBinding(Class bindingClass) throws BindingFactoryException;
    IBinding getBinding(Class bindingClass) throws BindingFactoryException;
}
