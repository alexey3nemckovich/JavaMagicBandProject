package main.com.bsuir.autoservice.binding;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.binding.log4j.Log4JTypeListener;
import main.com.bsuir.autoservice.binding.provider.BindingFactroryProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.impl.BeanMainCommand;
import main.com.bsuir.autoservice.command.impl.UserCommand;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;
import main.com.bsuir.autoservice.config.database.impl.sql.impl.SqlConfigDatabase;
import main.com.bsuir.autoservice.binding.provider.RequestControllerFactoryProvider;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.factory.IControllerFactory;
import main.com.bsuir.autoservice.controller.factory.impl.DefaultControllerFactory;
import main.com.bsuir.autoservice.controller.impl.BeanMainController;
import main.com.bsuir.autoservice.controller.impl.BeanTableController;
import main.com.bsuir.autoservice.controller.impl.NoController;
import main.com.bsuir.autoservice.controller.impl.UserController;
import main.com.bsuir.autoservice.controller.provider.IControllerProvider;
import main.com.bsuir.autoservice.controller.provider.impl.DefaultControllerProvider;
import main.com.bsuir.autoservice.dao.database.impl.sql.ISqlDatabase;
import main.com.bsuir.autoservice.dao.database.impl.sql.impl.SqlDatabase;
import main.com.bsuir.autoservice.dao.impl.order.IOrderDao;
import main.com.bsuir.autoservice.dao.impl.order.impl.OrderDao;
import main.com.bsuir.autoservice.dao.impl.user.IUserDao;
import main.com.bsuir.autoservice.dao.impl.user.impl.UserDao;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.dao.unitOfWork.impl.DefaultDaoUnitOfWork;
import main.com.bsuir.autoservice.dto.UserDTO;
import main.com.bsuir.autoservice.library.RequestType;
import main.com.bsuir.autoservice.library.mapper.IMapper;
import main.com.bsuir.autoservice.library.mapper.binding.factory.IBindingFactory;
import main.com.bsuir.autoservice.library.mapper.binding.factory.impl.DefaultBindingFactory;
import main.com.bsuir.autoservice.library.mapper.impl.DefaultMapper;
import main.com.bsuir.autoservice.service.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.impl.order.impl.OrderService;
import main.com.bsuir.autoservice.service.impl.user.IUserService;
import main.com.bsuir.autoservice.service.impl.user.impl.UserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import main.com.bsuir.autoservice.service.unitOfWork.impl.DefaultServiceUnitOfWork;

import java.util.List;
import java.util.Map;

public class BillingModule extends AbstractModule{
    @Override
    protected void configure() {
        bindConfigs();
        bindRequests();
        bindLibraries();
        bindDao();
        bindServices();
        bindCommands();
        bindControllers();
    }

    private void bindConfigs() {
        bindLog4J();
        bindDatabase();
    }

    private void bindLog4J() {
        bindListener(Matchers.any(), new Log4JTypeListener());
    }

    private void bindDatabase() {
        bind(String.class).annotatedWith(Names.named("sqlConfig")).toInstance("database");
        bind(ISqlConfigDatabase.class).to(SqlConfigDatabase.class).in(Singleton.class);
    }

    private void bindRequests() {
        bind(RequestType[].class).toInstance(new RequestType[]{RequestType.GET, RequestType.POST});
    }

    private void bindLibraries() {
        bind(IBindingFactory.class).annotatedWith(Names.named("provider")).to(DefaultBindingFactory.class).in(Singleton.class);
        bind(IBindingFactory.class).toProvider(BindingFactroryProvider.class).in(Singleton.class);
        bind(IMapper.class).to(DefaultMapper.class).in(Singleton.class);
    }

    private void bindDao() {
        bind(IUserDao.class).to(UserDao.class).in(Singleton.class);
        bind(IOrderDao.class).to(OrderDao.class).in(Singleton.class);
        bind(IDaoUnitOfWork.class).to(DefaultDaoUnitOfWork.class).in(Singleton.class);
        bind(ISqlDatabase.class).to(SqlDatabase.class).asEagerSingleton();
    }

    private void bindServices() {
        bind(IUserService.class).to(UserService.class).in(Singleton.class);
        bind(IOrderService.class).to(OrderService.class).in(Singleton.class);
        bind(IServiceUnitOfWork.class).to(DefaultServiceUnitOfWork.class).in(Singleton.class);
    }

    private void bindCommands(){
        bind(new TypeLiteral<ICommand<UserDTO,List<User>>>(){}).annotatedWith(Names.named("userCommand")).to(UserCommand.class).in(Singleton.class);
        bind(ICommand.class).annotatedWith(Names.named("beanMainCommand")).to(BeanMainCommand.class).in(Singleton.class);
    }

    private void bindControllers() {
        bind(IController.class).annotatedWith(Names.named("defaultController")).to(NoController.class).in(Singleton.class);
        bind(IControllerFactory.class).annotatedWith(Names.named("defaultControllerFactory")).to(DefaultControllerFactory.class).in(Singleton.class);
        bind(new TypeLiteral<Map<RequestType,IControllerFactory>>(){}).annotatedWith(Names.named("mapControllerFactory")).toProvider(RequestControllerFactoryProvider.class).in(Singleton.class);
        bind(IControllerProvider.class).to(DefaultControllerProvider.class).in(Singleton.class);
        bindConcreteControllers();
    }

    //ok
    private void bindConcreteControllers() {
        bind(UserController.class).in(Singleton.class);
        bind(BeanMainController.class).in(Singleton.class);
        bind(BeanTableController.class).in(Singleton.class);
    }
}
