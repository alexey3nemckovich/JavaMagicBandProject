package main.com.bsuir.autoservice.binding;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import main.com.bsuir.autoservice.binding.annotation.ControllerProviderArgument;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.binding.annotation.Supported;
import main.com.bsuir.autoservice.binding.log4j.Log4JTypeListener;
import main.com.bsuir.autoservice.binding.provider.BindingFactroryProvider;
import main.com.bsuir.autoservice.command.User.GetAllUserCommand;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;
import main.com.bsuir.autoservice.config.database.impl.sql.impl.SqlConfigDatabase;
import main.com.bsuir.autoservice.binding.provider.ControllerMapProvider;
import main.com.bsuir.autoservice.controller.ControllerId;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.NoController;
import main.com.bsuir.autoservice.controller.UserController;
import main.com.bsuir.autoservice.controller.provider.ControllerProvider;
import main.com.bsuir.autoservice.dao.database.impl.sql.ISqlDatabase;
import main.com.bsuir.autoservice.dao.database.impl.sql.impl.SqlDatabase;
import main.com.bsuir.autoservice.dao.impl.order.IOrderDao;
import main.com.bsuir.autoservice.dao.impl.order.impl.OrderDao;
import main.com.bsuir.autoservice.dao.impl.user.IUserDao;
import main.com.bsuir.autoservice.dao.impl.user.impl.UserDao;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.dao.unitOfWork.impl.DefaultDaoUnitOfWork;
import main.com.bsuir.autoservice.library.RequestType;
import main.com.bsuir.autoservice.http.parser.IHttpParser;
import main.com.bsuir.autoservice.library.binding.factory.IBindingFactory;
import main.com.bsuir.autoservice.library.binding.factory.impl.DefaultBindingFactory;
import main.com.bsuir.autoservice.http.parser.DefaultHttpParser;
import main.com.bsuir.autoservice.service.order.IOrderService;
import main.com.bsuir.autoservice.service.order.OrderService;
import main.com.bsuir.autoservice.service.user.IUserService;
import main.com.bsuir.autoservice.service.user.UserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import main.com.bsuir.autoservice.service.unitOfWork.DefaultServiceUnitOfWork;

import java.util.Map;

public class AutoServiceShopModule extends AbstractModule{
    @Override
    protected void configure() {
        bindConfig();
        bindDefault();
        bindSupported();
        bindLibraries();
        bindControllers();
        bindCommands();
        bindServices();
        bindDao();
    }

    private void bindDefault(){
        bind(IHttpParser.class).annotatedWith(Default.class).to(DefaultHttpParser.class).in(Singleton.class);
        bind(IController.class).annotatedWith(Default.class).to(NoController.class).in(Singleton.class);
        bind(IServiceUnitOfWork.class).annotatedWith(Default.class).to(DefaultServiceUnitOfWork.class).in(Singleton.class);
    }

    private void bindSupported(){
        bind(RequestType[].class).
                annotatedWith(Supported.class).
                toInstance(new RequestType[]{RequestType.GET, RequestType.POST});
    }

    private void bindConfig() {
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

    private void bindLibraries() {
        bind(IBindingFactory.class).annotatedWith(Names.named("provider")).to(DefaultBindingFactory.class).in(Singleton.class);
        bind(IBindingFactory.class).toProvider(BindingFactroryProvider.class).in(Singleton.class);
    }

    private void bindControllers() {
        bindConcreteControllers();
        bind(ControllerProvider.class).in(Singleton.class);
        bind(new TypeLiteral<Map<ControllerId, IController>>(){}).
                annotatedWith(ControllerProviderArgument.class).
                toProvider(ControllerMapProvider.class).
                in(Singleton.class);
    }

    private void bindConcreteControllers() {
        bind(UserController.class).in(Singleton.class);
    }

    private void bindCommands(){
        bind(GetAllUserCommand.class).in(Singleton.class);
    }

    private void bindServices() {
        bind(IUserService.class).to(UserService.class).in(Singleton.class);
        bind(IOrderService.class).to(OrderService.class).in(Singleton.class);
    }

    private void bindDao() {
        bind(IUserDao.class).to(UserDao.class).in(Singleton.class);
        bind(IOrderDao.class).to(OrderDao.class).in(Singleton.class);
        bind(IDaoUnitOfWork.class).to(DefaultDaoUnitOfWork.class).in(Singleton.class);
        bind(ISqlDatabase.class).to(SqlDatabase.class).asEagerSingleton();
    }
}
