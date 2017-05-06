package main.com.bsuir.autoservice.binding;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import main.com.bsuir.autoservice.binding.annotation.ControllerProviderArgument;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.binding.annotation.Supported;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanActionMap;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanAddActionMap;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanEditActionMap;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanViewActionMap;
import main.com.bsuir.autoservice.binding.log4j.Log4JTypeListener;
import main.com.bsuir.autoservice.binding.provider.impl.BindingFactroryProvider;
import main.com.bsuir.autoservice.binding.provider.impl.ControllerMapProvider;
import main.com.bsuir.autoservice.binding.provider.action.map.impl.BeanActionMapProvider;
import main.com.bsuir.autoservice.binding.provider.action.map.impl.BeanAddActionMapProvider;
import main.com.bsuir.autoservice.binding.provider.action.map.impl.BeanEditActionMapProvider;
import main.com.bsuir.autoservice.binding.provider.action.map.impl.BeanViewActionMapProvider;
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
import main.com.bsuir.autoservice.dao.crud.impl.discount.DiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.discount.IDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.discount_user.DiscountUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.discount_user.IDiscountUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.notification.INotificationDao;
import main.com.bsuir.autoservice.dao.crud.impl.notification.NotificationDao;
import main.com.bsuir.autoservice.dao.crud.impl.order.IOrderDao;
import main.com.bsuir.autoservice.dao.crud.impl.order.OrderDao;
import main.com.bsuir.autoservice.dao.crud.impl.order_spare_part.IOrderSparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.order_spare_part.OrderSparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.ordered_service.IOrderedServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.ordered_service.OrderedServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.service.IServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.service.ServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.service_shop.IServiceShopDao;
import main.com.bsuir.autoservice.dao.crud.impl.service_shop.ServiceShopDao;
import main.com.bsuir.autoservice.dao.crud.impl.share.IShareDao;
import main.com.bsuir.autoservice.dao.crud.impl.share.ShareDao;
import main.com.bsuir.autoservice.dao.crud.impl.share_discount.IShareDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.share_discount.ShareDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.spare_part.ISparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.spare_part.SparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.crud.impl.staff.StaffDao;
import main.com.bsuir.autoservice.dao.crud.impl.user.IUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.user.UserDao;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.SqlDatabase;
import main.com.bsuir.autoservice.dao.sql.ISql;
import main.com.bsuir.autoservice.dao.sql.Sql;
import main.com.bsuir.autoservice.dao.unitOfWork.DefaultDaoUnitOfWork;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.library.RequestType;
import main.com.bsuir.autoservice.library.binding.factory.IBindingFactory;
import main.com.bsuir.autoservice.library.binding.factory.impl.DefaultBindingFactory;
import main.com.bsuir.autoservice.service.BaseService;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.impl.discount.DiscountService;
import main.com.bsuir.autoservice.service.crud.impl.discount.IDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.crud.impl.notifiaction.NotificationService;
import main.com.bsuir.autoservice.service.crud.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.order.OrderService;
import main.com.bsuir.autoservice.service.crud.impl.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service.ServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service_shop.IServiceShopBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service_shop.ServiceShopBeanService;
import main.com.bsuir.autoservice.service.crud.impl.share.IShareService;
import main.com.bsuir.autoservice.service.crud.impl.share.ShareService;
import main.com.bsuir.autoservice.service.crud.impl.spare_part.ISparePartService;
import main.com.bsuir.autoservice.service.crud.impl.spare_part.SparePartService;
import main.com.bsuir.autoservice.service.crud.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.impl.staff.StaffService;
import main.com.bsuir.autoservice.service.crud.impl.user.IUserService;
import main.com.bsuir.autoservice.service.crud.impl.user.UserService;
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
        bindLibraries();
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

        bind(IDiscountService.class).to(DiscountService.class).in(Singleton.class);
        bind(INotificationService.class).to(NotificationService.class).in(Singleton.class);
        bind(IOrderService.class).to(OrderService.class).in(Singleton.class);
        bind(IServiceBeanService.class).to(ServiceBeanService.class).in(Singleton.class);
        bind(IServiceShopBeanService.class).to(ServiceShopBeanService.class).in(Singleton.class);
        bind(IShareService.class).to(ShareService.class).in(Singleton.class);
        bind(ISparePartService.class).to(SparePartService.class).in(Singleton.class);
        bind(IStaffService.class).to(StaffService.class).in(Singleton.class);
        bind(IUserService.class).to(UserService.class).in(Singleton.class);
    }

    private void bindDao() {
        bind(IDatabase.class).to(SqlDatabase.class).asEagerSingleton();
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
