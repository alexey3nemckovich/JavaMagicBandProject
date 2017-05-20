package main.com.bsuir.autoservice.binding;

import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import main.com.bsuir.autoservice.binding.annotation.*;
import main.com.bsuir.autoservice.binding.annotation.action.map.*;
import main.com.bsuir.autoservice.binding.log4j.Log4JTypeListener;
import main.com.bsuir.autoservice.binding.provider.CrudDaoFactoryProvider;
import main.com.bsuir.autoservice.binding.provider.PermissionProvider;
import main.com.bsuir.autoservice.binding.provider.action.map.impl.*;
import main.com.bsuir.autoservice.binding.provider.fakeUOF.FakeDaoUOFProvider;
import main.com.bsuir.autoservice.binding.provider.fakeUOF.FakeServiceUOFProvider;
import main.com.bsuir.autoservice.binding.provider.impl.ControllerMapProvider;
import main.com.bsuir.autoservice.command.crud.add.AddBeanCommand;
import main.com.bsuir.autoservice.command.crud.delete.DeleteBeanCommand;
import main.com.bsuir.autoservice.command.crud.delete.DeleteBeanDependencyCommand;
import main.com.bsuir.autoservice.command.crud.edit.EditBeanCommand;
import main.com.bsuir.autoservice.command.crud.get.*;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;
import main.com.bsuir.autoservice.command.param.BeanEditPageInfo;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.command.param.CrudPageInfo;
import main.com.bsuir.autoservice.config.RouteConfig;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;
import main.com.bsuir.autoservice.config.database.impl.sql.impl.SqlConfigDatabase;
import main.com.bsuir.autoservice.config.permission.Permission;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.NoController;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.controller.bean.*;
import main.com.bsuir.autoservice.controller.provider.ControllerProvider;
import main.com.bsuir.autoservice.controller.provider.IControllerProvider;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.database.map.impl.DataMapConfig;
import main.com.bsuir.autoservice.dao.database.nestedrequest.SqlDatabase;
import main.com.bsuir.autoservice.dao.database.nestedrequest.SqlRequestDatabase;
import main.com.bsuir.autoservice.dao.database.nestedrequest.SqlRequestDatabaseProvider;
import main.com.bsuir.autoservice.dao.impl.crudfactory.ICrudDaoFactory;
import main.com.bsuir.autoservice.dao.impl.crudfactory.impl.CrudDaoFactory;
import main.com.bsuir.autoservice.dao.impl.discount.DiscountDao;
import main.com.bsuir.autoservice.dao.impl.discount.IDiscountDao;
import main.com.bsuir.autoservice.dao.impl.discountuser.DiscountUserDao;
import main.com.bsuir.autoservice.dao.impl.discountuser.IDiscountUserDao;
import main.com.bsuir.autoservice.dao.impl.notification.INotificationDao;
import main.com.bsuir.autoservice.dao.impl.notification.NotificationDao;
import main.com.bsuir.autoservice.dao.impl.order.IOrderDao;
import main.com.bsuir.autoservice.dao.impl.order.OrderDao;
import main.com.bsuir.autoservice.dao.impl.orderedservice.IOrderedServiceDao;
import main.com.bsuir.autoservice.dao.impl.orderedservice.OrderedServiceDao;
import main.com.bsuir.autoservice.dao.impl.ordersparepart.IOrderSparePartDao;
import main.com.bsuir.autoservice.dao.impl.ordersparepart.OrderSparePartDao;
import main.com.bsuir.autoservice.dao.impl.service.IServiceDao;
import main.com.bsuir.autoservice.dao.impl.service.ServiceDao;
import main.com.bsuir.autoservice.dao.impl.serviceshop.IServiceShopDao;
import main.com.bsuir.autoservice.dao.impl.serviceshop.ServiceShopDao;
import main.com.bsuir.autoservice.dao.impl.share.IShareDao;
import main.com.bsuir.autoservice.dao.impl.share.ShareDao;
import main.com.bsuir.autoservice.dao.impl.sharediscount.IShareDiscountDao;
import main.com.bsuir.autoservice.dao.impl.sharediscount.ShareDiscountDao;
import main.com.bsuir.autoservice.dao.impl.sparepart.ISparePartDao;
import main.com.bsuir.autoservice.dao.impl.sparepart.SparePartDao;
import main.com.bsuir.autoservice.dao.impl.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.impl.staff.StaffDao;
import main.com.bsuir.autoservice.dao.impl.user.IUserDao;
import main.com.bsuir.autoservice.dao.impl.user.UserDao;
import main.com.bsuir.autoservice.dao.sql.ISql;
import main.com.bsuir.autoservice.dao.sql.Sql;
import main.com.bsuir.autoservice.dao.unitofwork.DefaultDaoUnitOfWork;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.infrastructure.cache.IMethodCache;
import main.com.bsuir.autoservice.infrastructure.cache.impl.MethodCache;
import main.com.bsuir.autoservice.infrastructure.interceptor.CacheInterceptor;
import main.com.bsuir.autoservice.infrastructure.interceptor.TransactionInterceptor;
import main.com.bsuir.autoservice.infrastructure.listener.DatabaseConnectionListener;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.infrastructure.session.impl.CustomHttpSession;
import main.com.bsuir.autoservice.infrastructure.session.impl.CustomHttpSessionProvider;
import main.com.bsuir.autoservice.infrastructure.transaction.ITransaction;
import main.com.bsuir.autoservice.infrastructure.transaction.impl.RequestTransaction;
import main.com.bsuir.autoservice.library.RequestType;
import main.com.bsuir.autoservice.service.impl.baseservice.BaseService;
import main.com.bsuir.autoservice.service.impl.baseservice.IBaseService;
import main.com.bsuir.autoservice.service.impl.crud.CrudService;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.impl.discount.DiscountService;
import main.com.bsuir.autoservice.service.impl.discount.IDiscountService;
import main.com.bsuir.autoservice.service.impl.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.impl.notifiaction.NotificationService;
import main.com.bsuir.autoservice.service.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.impl.order.OrderService;
import main.com.bsuir.autoservice.service.impl.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.impl.service.ServiceBeanService;
import main.com.bsuir.autoservice.service.impl.serviceshop.IServiceShopBeanService;
import main.com.bsuir.autoservice.service.impl.serviceshop.ServiceShopBeanService;
import main.com.bsuir.autoservice.service.impl.share.IShareService;
import main.com.bsuir.autoservice.service.impl.share.ShareService;
import main.com.bsuir.autoservice.service.impl.sparepart.ISparePartService;
import main.com.bsuir.autoservice.service.impl.sparepart.SparePartService;
import main.com.bsuir.autoservice.service.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.impl.staff.StaffService;
import main.com.bsuir.autoservice.service.impl.user.IUserService;
import main.com.bsuir.autoservice.service.impl.user.UserService;
import main.com.bsuir.autoservice.service.unitofwork.DefaultServiceUnitOfWork;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

import java.util.Map;

public abstract class AutoServiceShopModule extends ServletModule {

    private static final String RESOURCE_DATABASE = "database";
    private static final String ERROR_JSP_PAGE = "./error.jsp";
    private static final String RESOURCE_DATABASE_MAP = "databaseMap.xml";

    protected void configBindings() {
        bindConfig();
        bindDefault();
        bindSupported();
        bindInfrastructure();
        bindPages();
        bindController();
        bindCommand();
        bindCommandParams();
        bindService();
        bindDao();

        bindPermission();
        bindLibraries();
    }

    private void bindInfrastructure() {
        bindSession();
        bindListener();
        bindTransaction();
        bindCache();
    }

    private void bindCache() {
        bind(IMethodCache.class).to(MethodCache.class).in(Singleton.class);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cached.class),
                new CacheInterceptor(getProvider(IMethodCache.class)));
    }

    private void bindListener() {
        bind(DatabaseConnectionListener.class);
    }

    private void bindTransaction() {
        bind(ITransaction.class).to(RequestTransaction.class);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transaction.class),
                new TransactionInterceptor(getProvider(ITransaction.class)));
    }

    private void bindSession() {
        bind(IUserSession.class).to(CustomHttpSessionProvider.class);
        bind(CustomHttpSession.class);
    }

    private void bindPermission() {
        bind(new TypeLiteral<Map<String, Permission>>(){}).annotatedWith(PermissionUrl.class).
                toProvider(PermissionProvider.class).in(Singleton.class);
    }

    private void bindDefault(){
        bind(IController.class).annotatedWith(Default.class).to(NoController.class).in(Singleton.class);
    }

    private void bindSupported(){
        bind(RequestType[].class).
                annotatedWith(Supported.class).
                toInstance(new RequestType[]{RequestType.GET, RequestType.POST});
    }

    private void bindPages(){
        bind(String.class).annotatedWith(ErrorJspPage.class).toInstance(ERROR_JSP_PAGE);
    }

    private void bindController() {
        bindConcreteControllers();
        bindControllerActionMaps();
        bind(IControllerProvider.class).to(ControllerProvider.class).in(Singleton.class);
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
        bind(new TypeLiteral<Map<String, Action>>(){}).
                annotatedWith(BeanDependencyViewActionMap.class).
                toProvider(BeanDependencyViewActionMapProvider.class).in(Singleton.class);
        bind(new TypeLiteral<Map<String, Action>>(){}).
                annotatedWith(BeanDependencyEditActionMap.class).
                toProvider(BeanDependencyEditActionMapProvider.class).in(Singleton.class);
        bind(new TypeLiteral<Map<String, Action>>(){}).
                annotatedWith(BeanDependencyAddActionMap.class).
                toProvider(BeanDependencyAddActionMapProvider.class).in(Singleton.class);
    }

    private void bindConcreteControllers() {
        bind(BeanController.class).in(Singleton.class);
        bind(BeanAddController.class).in(Singleton.class);
        bind(BeanViewController.class).in(Singleton.class);
        bind(BeanEditController.class).in(Singleton.class);
        bind(BeanDependencyViewController.class).in(Singleton.class);
        bind(BeanDependencyEditController.class).in(Singleton.class);
        bind(BeanDependencyAddController.class).in(Singleton.class);
    }

    private void bindCommand(){
        //get
        bind(GetBeanAddPageCommand.class).in(Singleton.class);
        bind(GetBeanViewPageCommand.class).in(Singleton.class);
        bind(GetBeanMainPageCommand.class).in(Singleton.class);
        bind(GetBeanEditPageCommand.class).in(Singleton.class);
        bind(GetBeanDependencyViewPageCommand.class).in(Singleton.class);
        //delete
        bind(DeleteBeanDependencyCommand.class).in(Singleton.class);
        bind(DeleteBeanCommand.class).in(Singleton.class);
        //add
        bind(AddBeanCommand.class).in(Singleton.class);
        //edit
        bind(EditBeanCommand.class).in(Singleton.class);
    }

    private void bindCommandParams(){
        bind(CrudPageInfo.class);
        bind(BeanEditPageInfo.class);
        bind(BeanViewPageInfo.class);
        bind(BeanDependencyViewPageInfo.class);
    }

    private void bindService() {
        bind(IServiceUnitOfWork.class).to(DefaultServiceUnitOfWork.class).in(Singleton.class);
        bind(IServiceUnitOfWork.class).annotatedWith(FakeUOF.class).toProvider(FakeServiceUOFProvider.class).
                in(Singleton.class);

        bind(ICrudService.class).to(CrudService.class).in(Singleton.class);
        bind(IBaseService.class).to(BaseService.class).in(Singleton.class);
        bind(IUserService.class).to(UserService.class).in(Singleton.class);
        bind(IDiscountService.class).to(DiscountService.class).in(Singleton.class);
        bind(INotificationService.class).to(NotificationService.class).in(Singleton.class);
        bind(IOrderService.class).to(OrderService.class).in(Singleton.class);
        bind(IServiceBeanService.class).to(ServiceBeanService.class).in(Singleton.class);
        bind(IServiceShopBeanService.class).to(ServiceShopBeanService.class).in(Singleton.class);
        bind(IShareService.class).to(ShareService.class).in(Singleton.class);
        bind(ISparePartService.class).to(SparePartService.class).in(Singleton.class);
        bind(IStaffService.class).to(StaffService.class).in(Singleton.class);
    }

    private void bindDao() {
        bind(IDaoUnitOfWork.class).to(DefaultDaoUnitOfWork.class).in(Singleton.class);
        bind(IDaoUnitOfWork.class).annotatedWith(FakeUOF.class).toProvider(FakeDaoUOFProvider.class).in(Singleton.class);

        bind(ICrudDaoFactory.class).toProvider(CrudDaoFactoryProvider.class).in(Singleton.class);
        bind(ICrudDaoFactory.class).annotatedWith(Names.named("crudDaoFactory")).to(CrudDaoFactory.class).in(Singleton.class);

        bind(IDatabase.class).to(SqlRequestDatabaseProvider.class);
        bind(SqlRequestDatabase.class);
        bind(SqlDatabase.class).asEagerSingleton();
        bind(ISql.class).to(Sql.class).in(Singleton.class);

        bind(IDiscountDao.class).to(DiscountDao.class).in(Singleton.class);
        bind(IDiscountUserDao.class).to(DiscountUserDao.class).in(Singleton.class);
        bind(INotificationDao.class).to(NotificationDao.class).in(Singleton.class);
        bind(IOrderDao.class).to(OrderDao.class).in(Singleton.class);
        bind(IOrderSparePartDao.class).to(OrderSparePartDao.class).in(Singleton.class);
        bind(IOrderedServiceDao.class).to(OrderedServiceDao.class).in(Singleton.class);
        bind(IServiceDao.class).to(ServiceDao.class).in(Singleton.class);
        bind(IServiceShopDao.class).to(ServiceShopDao.class).in(Singleton.class);
        bind(IShareDao.class).to(ShareDao.class).in(Singleton.class);
        bind(IShareDiscountDao.class).to(ShareDiscountDao.class).in(Singleton.class);
        bind(ISparePartDao.class).to(SparePartDao.class).in(Singleton.class);
        bind(IStaffDao.class).to(StaffDao.class).in(Singleton.class);
        bind(IUserDao.class).to(UserDao.class).in(Singleton.class);
    }

    private void bindConfig() {
        bindLog4J();
        bindDatabase();
        bindRoute();
    }

    private void bindRoute() {
        bind(RouteConfig.class).in(Singleton.class);
    }

    private void bindLog4J() {
        bindListener(Matchers.any(), new Log4JTypeListener());
    }

    private void bindDatabase() {
        bind(String.class).annotatedWith(Names.named("sqlConfig")).toInstance(RESOURCE_DATABASE);
        bind(ISqlConfigDatabase.class).to(SqlConfigDatabase.class).in(Singleton.class);

        bind(String.class).annotatedWith(Names.named("dataMapConfig")).toInstance(RESOURCE_DATABASE_MAP);
        bind(IDatabaseMap.class).to(DataMapConfig.class).asEagerSingleton();
    }

    private void bindLibraries() {
    }
}
