package main.com.bsuir.autoservice.controller.provider.impl;

import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.IControllerFactory;
import main.com.bsuir.autoservice.controller.factory.exception.ControllerFactoryException;
import main.com.bsuir.autoservice.controller.factory.impl.DefaultControllerFactory;
import main.com.bsuir.autoservice.controller.provider.IControllerProvider;
import main.com.bsuir.autoservice.controller.provider.exception.ControllerProviderException;
import main.com.bsuir.autoservice.library.mapper.IMapper;
import main.com.bsuir.autoservice.library.mapper.binding.factory.IBindingFactory;
import main.com.bsuir.autoservice.library.mapper.binding.factory.exception.BindingFactoryException;
import main.com.bsuir.autoservice.library.mapper.binding.factory.impl.DefaultBindingFactory;
import main.com.bsuir.autoservice.library.mapper.binding.impl.IntegerBinding;
import main.com.bsuir.autoservice.library.mapper.binding.impl.StringBinding;
import main.com.bsuir.autoservice.library.mapper.impl.DefaultMapper;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.impl.UserCommand;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DefaultControllerProvider implements IControllerProvider {
    public DefaultControllerProvider(RequestType[] supportedRequestType) {
        this.requestControllerFactory = createRequestFactory(supportedRequestType);
        registerAllBindings(createMapper());
    }

    private static Map<RequestType, IControllerFactory> createRequestFactory(RequestType[] supportedRequestType) {
        Map<RequestType, IControllerFactory> map = new HashMap<RequestType, IControllerFactory>();
        for (RequestType requestType : supportedRequestType)
            map.put(requestType, new DefaultControllerFactory());
        return map;
    };

    private static IMapper createMapper() {
        IBindingFactory bindingFactory = createBindingFactory();
        return new DefaultMapper(bindingFactory);
    }

    private static IBindingFactory createBindingFactory(){
        try {
            IBindingFactory bindingFactory = new DefaultBindingFactory();
            addMapperBindings(bindingFactory);
            return bindingFactory;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void addRequestBind(RequestType requestType, String url, IController controller) throws ControllerFactoryException {
        addMapBind(requestControllerFactory.get(requestType),url,controller);
    }

    private void addMapBind(IControllerFactory commandFactory, String url, IController controller) throws ControllerFactoryException {
        commandFactory.addController(url, controller);
    }

    private void registerAllBindings(IMapper binder) {
        try {
            addGetBind(binder);
            addPostBind(binder);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void addMapperBindings(IBindingFactory bindingFactory) throws BindingFactoryException {
        bindingFactory.addBinding(String.class, new StringBinding());
        bindingFactory.addBinding(Integer.class, new IntegerBinding());
    }

    private void addPostBind(IMapper binder)  throws ControllerFactoryException {
    }

    private void addGetBind(IMapper binder) throws ControllerFactoryException {
        //addRequestBind(RequestType.GET, "/bean/user",new User(binder));
    }

    @Override
    public IController getController(RequestType requestType, String url)
            throws ControllerProviderException {
        try {
            return requestControllerFactory.get(requestType).getController(url);
        }catch (Exception e){
            throw new ControllerProviderException(e);
        }
    }

    private final Map<RequestType, IControllerFactory> requestControllerFactory;
}