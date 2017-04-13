package main.com.bsuir.autoservice.library.binding.factory.impl;

import main.com.bsuir.autoservice.library.DefaultHashMap;
import main.com.bsuir.autoservice.library.binding.IBinding;
import main.com.bsuir.autoservice.library.binding.factory.IBindingFactory;
import main.com.bsuir.autoservice.library.binding.factory.exception.BindingFactoryException;
import main.com.bsuir.autoservice.library.binding.impl.DefaultBinding;

import java.util.Map;

public class DefaultBindingFactory implements IBindingFactory{
    public DefaultBindingFactory(){
        bindingMap = new DefaultHashMap<>(new DefaultBinding());
    }

    @Override
    public void addBinding(Class bindingClass, IBinding binding) throws BindingFactoryException {
        try {
            bindingMap.put(bindingClass,binding);
        } catch (Exception e){
            throw new BindingFactoryException(e);
        }
    }

    @Override
    public void removeBinding(Class bindingClass) throws BindingFactoryException {
        try {
            if (bindingMap.containsKey(bindingClass)) {
                bindingMap.remove(bindingClass);
            }else {
                //TODO: log that not having key
            }
        } catch (Exception e){
            throw new BindingFactoryException(e);
        }
    }

    @Override
    public IBinding getBinding(Class bindingClass) throws BindingFactoryException {
        try {
            return bindingMap.get(bindingClass);
        }catch (Exception e){
            throw new BindingFactoryException(e);
        }
    }

    private final Map<Class,IBinding> bindingMap;
}
