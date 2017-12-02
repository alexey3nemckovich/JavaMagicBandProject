package main.com.bsuir.autoservice.binding;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import main.com.bsuir.autoservice.binding.annotation.ControllerProviderArgument;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.binding.annotation.Supported;
import main.com.bsuir.autoservice.binding.annotation.action.map.*;
import main.com.bsuir.autoservice.binding.log4j.Log4JTypeListener;
import main.com.bsuir.autoservice.binding.provider.action.map.impl.*;
import main.com.bsuir.autoservice.binding.provider.impl.BindingFactroryProvider;
import main.com.bsuir.autoservice.binding.provider.impl.ControllerMapProvider;
import main.com.bsuir.autoservice.command.crud.add.AddBeanCommand;
import main.com.bsuir.autoservice.command.crud.delete.DeleteBeanCommand;
import main.com.bsuir.autoservice.command.crud.delete.DeleteBeanDependencyCommand;
import main.com.bsuir.autoservice.command.crud.edit.EditBeanCommand;
import main.com.bsuir.autoservice.command.crud.get.*;
import main.com.bsuir.autoservice.command.param.*;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;
import main.com.bsuir.autoservice.config.database.impl.sql.impl.SqlConfigDatabase;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.NoController;
import main.com.bsuir.autoservice.controller.action.Action;
import main.com.bsuir.autoservice.controller.bean.*;
import main.com.bsuir.autoservice.controller.provider.ControllerProvider;
import main.com.bsuir.autoservice.dao.crud.impl.backup.discount.DiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.discount.IDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.discount_user.DiscountUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.discount_user.IDiscountUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.notification.INotificationDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.notification.NotificationDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.order.IOrderDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.order.OrderDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.order_spare_part.IOrderSparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.order_spare_part.OrderSparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.ordered_service.IOrderedServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.ordered_service.OrderedServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.service.IServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.service.ServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.service_shop.IServiceShopDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.service_shop.ServiceShopDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.share.IShareDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.share.ShareDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.share_discount.IShareDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.share_discount.ShareDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.spare_part.ISparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.spare_part.SparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.staff.StaffDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.user.IUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.user.UserDao;
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
import main.com.bsuir.autoservice.service.crud.impl.discount_user.DiscountUserService;
import main.com.bsuir.autoservice.service.crud.impl.discount_user.IDiscountUserService;
import main.com.bsuir.autoservice.service.crud.impl.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.crud.impl.notifiaction.NotificationService;
import main.com.bsuir.autoservice.service.crud.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.order.OrderService;
import main.com.bsuir.autoservice.service.crud.impl.order_spare_part.IOrderSparePartService;
import main.com.bsuir.autoservice.service.crud.impl.order_spare_part.OrderSparePartService;
import main.com.bsuir.autoservice.service.crud.impl.ordered_service.IOrderedServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.ordered_service.OrderedServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service.ServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service_shop.IServiceShopBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service_shop.ServiceShopBeanService;
import main.com.bsuir.autoservice.service.crud.impl.share.IShareService;
import main.com.bsuir.autoservice.service.crud.impl.share.ShareService;
import main.com.bsuir.autoservice.service.crud.impl.share_discount.IShareDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.share_discount.ShareDiscountService;
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
        bind(IService.class).to(BaseService.class).in(Singleton.class);

        bind(IDiscountService.class).to(DiscountService.class).in(Singleton.class);
        bind(IDiscountUserService.class).to(DiscountUserService.class).in(Singleton.class);
        bind(INotificationService.class).to(NotificationService.class).in(Singleton.class);
        bind(IOrderService.class).to(OrderService.class).in(Singleton.class);
        bind(IOrderedServiceBeanService.class).to(OrderedServiceBeanService.class).in(Singleton.class);
        bind(IOrderSparePartService.class).to(OrderSparePartService.class).in(Singleton.class);
        bind(IServiceBeanService.class).to(ServiceBeanService.class).in(Singleton.class);
        bind(IServiceShopBeanService.class).to(ServiceShopBeanService.class).in(Singleton.class);
        bind(IShareService.class).to(ShareService.class).in(Singleton.class);
        bind(IShareDiscountService.class).to(ShareDiscountService.class).in(Singleton.class);
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
