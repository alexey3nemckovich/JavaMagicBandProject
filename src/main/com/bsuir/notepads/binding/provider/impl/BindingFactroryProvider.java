package main.com.bsuir.notepads.binding.provider.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import main.com.bsuir.notepads.library.binding.factory.IBindingFactory;
import main.com.bsuir.notepads.library.binding.factory.exception.BindingFactoryException;
import main.com.bsuir.notepads.library.binding.impl.IntegerBinding;
import main.com.bsuir.notepads.library.binding.impl.StringBinding;

public class BindingFactroryProvider implements Provider<IBindingFactory> {
    private final IBindingFactory bindingFactory;

    @Inject
    public BindingFactroryProvider( @Named("provider") IBindingFactory bindingFactory) throws BindingFactoryException {
        this.bindingFactory = bindingFactory;
        updateBindings();
    }

    private void updateBindings() throws BindingFactoryException {
        bindingFactory.addBinding(String.class, new StringBinding());
        bindingFactory.addBinding(Integer.class, new IntegerBinding());
    }

    @Override
    public IBindingFactory get() {
        return bindingFactory;
    }
}
