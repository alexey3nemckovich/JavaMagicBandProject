package main.com.bsuir.autoservice.binding;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.google.inject.util.Providers;
import main.com.bsuir.autoservice.binding.annotation.ControllerProviderArgument;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.binding.annotation.Supported;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanActionMap;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanAddActionMap;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanEditActionMap;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanViewActionMap;
import main.com.bsuir.autoservice.binding.annotation.permission.Permission;
import main.com.bsuir.autoservice.binding.annotation.permission.PermissionInterceptor;
import main.com.bsuir.autoservice.binding.log4j.Log4JTypeListener;
import main.com.bsuir.autoservice.binding.provider.BindingFactroryProvider;
import main.com.bsuir.autoservice.binding.provider.ControllerMapProvider;
import main.com.bsuir.autoservice.binding.provider.action.map.BeanActionMapProvider;
import main.com.bsuir.autoservice.binding.provider.action.map.BeanAddActionMapProvider;
import main.com.bsuir.autoservice.binding.provider.action.map.BeanEditActionMapProvider;
import main.com.bsuir.autoservice.binding.provider.action.map.BeanViewActionMapProvider;
import main.com.bsuir.autoservice.command.ICommand;
import main.com.bsuir.autoservice.command.bean.page.crud.GetBeanAddPageCommand;
import main.com.bsuir.autoservice.command.bean.page.crud.GetBeanEditPageCommand;
import main.com.bsuir.autoservice.command.bean.page.main.GetBeanMainPageCommand;
import main.com.bsuir.autoservice.command.bean.page.view.GetBeanViewPageCommand;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.command.param.EditPageInfo;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;
import main.com.bsuir.autoservice.config.database.impl.sql.impl.SqlConfigDatabase;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.NoController;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.controller.bean.BeanAddController;
import main.com.bsuir.autoservice.controller.bean.BeanController;
import main.com.bsuir.autoservice.controller.bean.BeanEditController;
import main.com.bsuir.autoservice.controller.bean.BeanViewController;
import main.com.bsuir.autoservice.controller.provider.ControllerProvider;
import main.com.bsuir.autoservice.dao.crud.order.IOrderDao;
import main.com.bsuir.autoservice.dao.crud.order.OrderDao;
import main.com.bsuir.autoservice.dao.crud.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.crud.staff.StaffDao;
import main.com.bsuir.autoservice.dao.crud.user.IUserDao;
import main.com.bsuir.autoservice.dao.crud.user.UserDao;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.SqlDatabase;
import main.com.bsuir.autoservice.dao.sql.ISql;
import main.com.bsuir.autoservice.dao.sql.Sql;
import main.com.bsuir.autoservice.dao.unitOfWork.DefaultDaoUnitOfWork;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.library.RequestType;
import main.com.bsuir.autoservice.library.binding.factory.IBindingFactory;
import main.com.bsuir.autoservice.library.binding.factory.impl.DefaultBindingFactory;
import main.com.bsuir.autoservice.service.*;
import main.com.bsuir.autoservice.service.crud.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.order.OrderService;
import main.com.bsuir.autoservice.service.crud.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.staff.StaffService;
import main.com.bsuir.autoservice.service.crud.user.IUserService;
import main.com.bsuir.autoservice.service.crud.user.UserService;
import main.com.bsuir.autoservice.service.unitOfWork.DefaultServiceUnitOfWork;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import java.util.Map;

public class AutoServiceShopModule extends AbstractModule{
    @Override
    protected void configure() {
        bindDefault();
        bindSupported();
        bindController();
        bindCommand();
        bindCommandParams();
        bindService();
        bindDao();

        /*unchecked*/
        bindConfig();
        bindPermission();
        bindLibraries();
    }

    private void bindPermission() {
        bindInterceptor(Matchers.subclassesOf(ICommand.class), Matchers.annotatedWith(Permission.class),
                new PermissionInterceptor());
    }

    private void bindDefault(){
        bind(IController.class).annotatedWith(Default.class).to(NoController.class).in(Singleton.class);
        bind(IServiceUnitOfWork.class).annotatedWith(Default.class).to(DefaultServiceUnitOfWork.class).in(Singleton.class);
        bind(IDaoUnitOfWork.class).annotatedWith(Default.class).to(DefaultDaoUnitOfWork.class).in(Singleton.class);
    }

    private void bindSupported(){
        bind(RequestType[].class).
                annotatedWith(Supported.class).
                toInstance(new RequestType[]{RequestType.GET, RequestType.POST});
    }

    private void bindController() {
        bindConcreteControllers();
        bindControllerActionMaps();
        bind(ControllerProvider.class).in(Singleton.class);
        bind(new TypeLiteral<Map<String, IController>>(){}).
                annotatedWith(ControllerProviderArgument.class).
                toProvider(ControllerMapProvider.class).
                in(Singleton.class);
    }

    private void bindControllerActionMaps(){
        bind(new TypeLiteral<Map<String, Action>>(){}).
                annotatedWith(BeanActionMap.class).
                toProvider(BeanActionMapProvider.class).in(Singleton.class);
        bind(new TypeLiteral<Map<String, Action>>(){}).
                annotatedWith(BeanAddActionMap.class).
                toProvider(BeanAddActionMapProvider.class).in(Singleton.class);
        bind(new TypeLiteral<Map<String, Action>>(){}).
                annotatedWith(BeanViewActionMap.class).
                toProvider(BeanViewActionMapProvider.class).in(Singleton.class);
        bind(new TypeLiteral<Map<String, Action>>(){}).
                annotatedWith(BeanEditActionMap.class).
                toProvider(BeanEditActionMapProvider.class).in(Singleton.class);
    }

    private void bindConcreteControllers() {
        bind(BeanController.class).in(Singleton.class);
        bind(BeanAddController.class).in(Singleton.class);
        bind(BeanViewController.class).in(Singleton.class);
        bind(BeanEditController.class).in(Singleton.class);
    }

    private void bindCommand(){
        bind(GetBeanAddPageCommand.class).in(Singleton.class);
        bind(GetBeanViewPageCommand.class).in(Singleton.class);
        bind(GetBeanMainPageCommand.class).in(Singleton.class);
        bind(GetBeanEditPageCommand.class).in(Singleton.class);
    }

    private void bindCommandParams(){
        bind(CrudPageInfo.class);
        bind(EditPageInfo.class);
        bind(BeanViewPageInfo.class);
    }

    private void bindService() {
        bind(IService.class).to(BaseService.class).in(Singleton.class);
        bind(IUserService.class).to(UserService.class).in(Singleton.class);
        bind(IOrderService.class).to(OrderService.class).in(Singleton.class);
        bind(IStaffService.class).to(StaffService.class).in(Singleton.class);
        bind(IShareService.class).toProvider(Providers.of(null)).in(Singleton.class);
        bind(IServiceService.class).toProvider(Providers.of(null)).in(Singleton.class);
        bind(INotificationService.class).toProvider(Providers.of(null)).in(Singleton.class);
    }

    private void bindDao() {
        bind(IUserDao.class).to(UserDao.class).in(Singleton.class);
        bind(IOrderDao.class).to(OrderDao.class).in(Singleton.class);
        bind(IStaffDao.class).to(StaffDao.class).in(Singleton.class);
        bind(IDatabase.class).to(SqlDatabase.class).asEagerSingleton();
        bind(ISql.class).to(Sql.class).in(Singleton.class);
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
}
