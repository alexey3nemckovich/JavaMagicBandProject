package main.com.bsuir.autoservice.controller.provider.impl;

import main.com.bsuir.autoservice.command.factory.ICommandFactory;
import main.com.bsuir.autoservice.command.factory.exception.CommandFactoryException;
import main.com.bsuir.autoservice.command.factory.impl.DefaultCommandFactory;
import main.com.bsuir.autoservice.command.impl.BeanMainCommand;
import main.com.bsuir.autoservice.command.impl.UserCommand;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.IControllerFactory;
import main.com.bsuir.autoservice.controller.factory.exception.ControllerFactoryException;
import main.com.bsuir.autoservice.controller.factory.impl.DefaultControllerFactory;
import main.com.bsuir.autoservice.controller.impl.BeanMainController;
import main.com.bsuir.autoservice.controller.impl.UserController;
import main.com.bsuir.autoservice.controller.provider.IControllerProvider;
import main.com.bsuir.autoservice.controller.provider.exception.ControllerProviderException;
import main.com.bsuir.autoservice.dao.impl.UserDao.impl.UserDaoController;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.dao.unitOfWork.impl.DefaultDaoUnitOfWork;
import main.com.bsuir.autoservice.library.mapper.IMapper;
import main.com.bsuir.autoservice.library.mapper.binding.factory.IBindingFactory;
import main.com.bsuir.autoservice.library.mapper.binding.factory.exception.BindingFactoryException;
import main.com.bsuir.autoservice.library.mapper.binding.factory.impl.DefaultBindingFactory;
import main.com.bsuir.autoservice.library.mapper.binding.impl.IntegerBinding;
import main.com.bsuir.autoservice.library.mapper.binding.impl.StringBinding;
import main.com.bsuir.autoservice.library.mapper.impl.DefaultMapper;
import main.com.bsuir.autoservice.library.RequestType;
import main.com.bsuir.autoservice.service.impl.userService.impl.UserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import main.com.bsuir.autoservice.service.unitOfWork.impl.DefaultServiceUnitOfWork;

import java.util.HashMap;
import java.util.Map;

//TODO: moved createFactories in other classes
public class DefaultControllerProvider implements IControllerProvider {
    public DefaultControllerProvider(RequestType[] supportedRequestType) throws CommandFactoryException {
        this.requestControllerFactory = createRequestFactory(supportedRequestType);
        this.commandFactory = createCommandFactory();
        registerAllBindings(createMapper());
    }


    private static IServiceUnitOfWork createServices() {
        IDaoUnitOfWork daoUnitOfWork = createDao();
        return new DefaultServiceUnitOfWork(new UserService(daoUnitOfWork.getUserDao()));
    }

    private static IDaoUnitOfWork createDao() {
        return new DefaultDaoUnitOfWork(new UserDaoController());
    }

    private static ICommandFactory createCommandFactory() throws CommandFactoryException {
        ICommandFactory commandFactory = new DefaultCommandFactory();
        registerAllCommands(commandFactory);
        return commandFactory;
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

    private void registerAllBindings(IMapper mapper) {
        try {
            addGetBind(mapper);
            addPostBind(mapper);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void addMapperBindings(IBindingFactory bindingFactory) throws BindingFactoryException {
        bindingFactory.addBinding(String.class, new StringBinding());
        bindingFactory.addBinding(Integer.class, new IntegerBinding());
    }

    private void addPostBind(IMapper mapper)  throws ControllerFactoryException {
    }

    private void addGetBind(IMapper mapper) throws ControllerFactoryException, CommandFactoryException {
        addRequestBind(RequestType.GET, "/bean/user",
                new UserController(mapper,commandFactory.getCommand("userCommand")));
        addRequestBind(RequestType.GET, "/bean/main",
                new BeanMainController(commandFactory.getCommand("beanMainCommand")));
    }

    private static void registerAllCommands(ICommandFactory commandFactory) throws CommandFactoryException {
        IServiceUnitOfWork serviceUnitOfWork = createServices();
        commandFactory.addCommand("userCommand",new UserCommand(serviceUnitOfWork));
        commandFactory.addCommand("beanMainCommand", new BeanMainCommand(serviceUnitOfWork));
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
    private final ICommandFactory commandFactory;
}