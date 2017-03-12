package main.com.bsuir.autoservice.command.provider.impl;

import main.com.bsuir.autoservice.library.mapper.IMapper;
import main.com.bsuir.autoservice.library.mapper.binding.IBinding;
import main.com.bsuir.autoservice.library.mapper.binding.factory.IBindingFactory;
import main.com.bsuir.autoservice.library.mapper.binding.factory.exception.BindingFactoryException;
import main.com.bsuir.autoservice.library.mapper.binding.factory.impl.DefaultBindingFactory;
import main.com.bsuir.autoservice.library.mapper.binding.impl.IntegerBinding;
import main.com.bsuir.autoservice.library.mapper.binding.impl.StringBinding;
import main.com.bsuir.autoservice.library.mapper.impl.DefaultMapper;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.impl.UserCommand;
import main.com.bsuir.autoservice.command.provider.ICommandProvider;
import main.com.bsuir.autoservice.command.provider.exception.CommandProviderException;
import main.com.bsuir.autoservice.command.factory.ICommandFactory;
import main.com.bsuir.autoservice.command.factory.exception.CommandFactoryException;
import main.com.bsuir.autoservice.command.factory.impl.DefaultCommandFactory;
import main.com.bsuir.autoservice.library.RequestType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider implements ICommandProvider {
    public CommandProvider(RequestType[] supportedRequestType) {
        this.requestCommandFactory = createRequestFactory(supportedRequestType);
        registerAllBindings(createMapper());
    }

    private static Map<RequestType, ICommandFactory> createRequestFactory(RequestType[] supportedRequestType) {
        Map<RequestType, ICommandFactory> map = new HashMap<RequestType, ICommandFactory>();
        for (RequestType requestType : supportedRequestType)
            map.put(requestType, new DefaultCommandFactory());
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

    private void addRequestBind(RequestType requestType, String url, ICommand command) throws CommandFactoryException {
        addMapBind(requestCommandFactory.get(requestType),url,command);
    }

    private void addMapBind(ICommandFactory commandFactory, String url, ICommand command) throws CommandFactoryException {
        commandFactory.addCommand(url, command);
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

    private void addPostBind(IMapper binder)  throws CommandFactoryException {
    }

    private void addGetBind(IMapper binder) throws CommandFactoryException {
        addRequestBind(RequestType.GET, "/bean/user",new UserCommand(binder));
    }

    @Override
    public void invokeCommand(RequestType requestType, String url, HttpServletRequest request, HttpServletResponse response)
            throws CommandProviderException {
        try {
            ICommand command = requestCommandFactory.get(requestType).getCommand(url);
            command.execute(request, response);
        }catch (Exception e){
            throw new CommandProviderException(e);
        }
    }

    private final Map<RequestType,ICommandFactory> requestCommandFactory;
}