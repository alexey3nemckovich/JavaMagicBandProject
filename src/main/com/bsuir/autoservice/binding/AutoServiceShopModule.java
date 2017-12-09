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
import main.com.bsuir.autoservice.dao.crud.impl.address.AddressDao;
import main.com.bsuir.autoservice.dao.crud.impl.address.IAddressDao;
import main.com.bsuir.autoservice.dao.crud.impl.car.CarDao;
import main.com.bsuir.autoservice.dao.crud.impl.car.ICarDao;
import main.com.bsuir.autoservice.dao.crud.impl.car_status.CarStatusDao;
import main.com.bsuir.autoservice.dao.crud.impl.car_status.ICarStatusDao;
import main.com.bsuir.autoservice.dao.crud.impl.city.CityDao;
import main.com.bsuir.autoservice.dao.crud.impl.city.ICityDao;
import main.com.bsuir.autoservice.dao.crud.impl.driver.DriverDao;
import main.com.bsuir.autoservice.dao.crud.impl.driver.IDriverDao;
import main.com.bsuir.autoservice.dao.crud.impl.driver_car.DriverCarDao;
import main.com.bsuir.autoservice.dao.crud.impl.driver_car.IDriverCarDao;
import main.com.bsuir.autoservice.dao.crud.impl.driver_status.DriverStatusDao;
import main.com.bsuir.autoservice.dao.crud.impl.driver_status.IDriverStatusDao;
import main.com.bsuir.autoservice.dao.crud.impl.order_status.IOrderStatusDao;
import main.com.bsuir.autoservice.dao.crud.impl.order_status.OrderStatusDao;
import main.com.bsuir.autoservice.dao.crud.impl.ordered_product.IOrderedProductDao;
import main.com.bsuir.autoservice.dao.crud.impl.ordered_product.OrderedProductDao;
import main.com.bsuir.autoservice.dao.crud.impl.payment.IPaymentDao;
import main.com.bsuir.autoservice.dao.crud.impl.payment.PaymentDao;
import main.com.bsuir.autoservice.dao.crud.impl.phone.IPhoneDao;
import main.com.bsuir.autoservice.dao.crud.impl.phone.PhoneDao;
import main.com.bsuir.autoservice.dao.crud.impl.phone_operator.IPhoneOperatorDao;
import main.com.bsuir.autoservice.dao.crud.impl.phone_operator.PhoneOperatorDao;
import main.com.bsuir.autoservice.dao.crud.impl.product.IProductDao;
import main.com.bsuir.autoservice.dao.crud.impl.product.ProductDao;
import main.com.bsuir.autoservice.dao.crud.impl.product_type.IProductTypeDao;
import main.com.bsuir.autoservice.dao.crud.impl.product_type.ProductTypeDao;
import main.com.bsuir.autoservice.dao.crud.impl.registration.IRegistrationDao;
import main.com.bsuir.autoservice.dao.crud.impl.registration.RegistrationDao;
import main.com.bsuir.autoservice.dao.crud.impl.report.IReportDao;
import main.com.bsuir.autoservice.dao.crud.impl.report.ReportDao;
import main.com.bsuir.autoservice.dao.crud.impl.shop.IShopDao;
import main.com.bsuir.autoservice.dao.crud.impl.shop.ShopDao;
import main.com.bsuir.autoservice.dao.crud.impl.shop_product.IShopProductDao;
import main.com.bsuir.autoservice.dao.crud.impl.shop_product.ShopProductDao;
import main.com.bsuir.autoservice.dao.crud.impl.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.crud.impl.staff.StaffDao;
import main.com.bsuir.autoservice.dao.crud.impl.staff_position.IStaffPositionDao;
import main.com.bsuir.autoservice.dao.crud.impl.staff_position.StaffPositionDao;
import main.com.bsuir.autoservice.dao.crud.impl.user.IUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.user.UserDao;
import main.com.bsuir.autoservice.dao.crud.impl.user_type.IUserTypeDao;
import main.com.bsuir.autoservice.dao.crud.impl.user_type.UserTypeDao;
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
import main.com.bsuir.autoservice.service.crud.impl.address.AddressService;
import main.com.bsuir.autoservice.service.crud.impl.address.IAddressService;
import main.com.bsuir.autoservice.service.crud.impl.car.CarService;
import main.com.bsuir.autoservice.service.crud.impl.car.ICarService;
import main.com.bsuir.autoservice.service.crud.impl.car_status.CarStatusService;
import main.com.bsuir.autoservice.service.crud.impl.car_status.ICarStatusService;
import main.com.bsuir.autoservice.service.crud.impl.city.CityService;
import main.com.bsuir.autoservice.service.crud.impl.city.ICityService;
import main.com.bsuir.autoservice.service.crud.impl.driver.DriverService;
import main.com.bsuir.autoservice.service.crud.impl.driver.IDriverService;
import main.com.bsuir.autoservice.service.crud.impl.driver_car.DriverCarService;
import main.com.bsuir.autoservice.service.crud.impl.driver_car.IDriverCarService;
import main.com.bsuir.autoservice.service.crud.impl.driver_status.DriverStatusService;
import main.com.bsuir.autoservice.service.crud.impl.driver_status.IDriverStatusService;
import main.com.bsuir.autoservice.service.crud.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.order.OrderService;
import main.com.bsuir.autoservice.service.crud.impl.order_status.IOrderStatusService;
import main.com.bsuir.autoservice.service.crud.impl.order_status.OrderStatusService;
import main.com.bsuir.autoservice.service.crud.impl.ordered_product.IOrderedProductService;
import main.com.bsuir.autoservice.service.crud.impl.ordered_product.OrderedProductService;
import main.com.bsuir.autoservice.service.crud.impl.payment.IPaymentService;
import main.com.bsuir.autoservice.service.crud.impl.payment.PaymentService;
import main.com.bsuir.autoservice.service.crud.impl.phone.IPhoneService;
import main.com.bsuir.autoservice.service.crud.impl.phone.PhoneService;
import main.com.bsuir.autoservice.service.crud.impl.phone_operator.IPhoneOperatorService;
import main.com.bsuir.autoservice.service.crud.impl.phone_operator.PhoneOperatorService;
import main.com.bsuir.autoservice.service.crud.impl.product.IProductService;
import main.com.bsuir.autoservice.service.crud.impl.product.ProductService;
import main.com.bsuir.autoservice.service.crud.impl.product_type.IProductTypeService;
import main.com.bsuir.autoservice.service.crud.impl.product_type.ProductTypeService;
import main.com.bsuir.autoservice.service.crud.impl.registration.IRegistrationService;
import main.com.bsuir.autoservice.service.crud.impl.registration.RegistrationService;
import main.com.bsuir.autoservice.service.crud.impl.report.IReportService;
import main.com.bsuir.autoservice.service.crud.impl.report.ReportService;
import main.com.bsuir.autoservice.service.crud.impl.shop.IShopService;
import main.com.bsuir.autoservice.service.crud.impl.shop.ShopService;
import main.com.bsuir.autoservice.service.crud.impl.shop_product.IShopProductService;
import main.com.bsuir.autoservice.service.crud.impl.shop_product.ShopProductService;
import main.com.bsuir.autoservice.service.crud.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.impl.staff.StaffService;
import main.com.bsuir.autoservice.service.crud.impl.staff_position.IStaffPositionService;
import main.com.bsuir.autoservice.service.crud.impl.staff_position.StaffPositionService;
import main.com.bsuir.autoservice.service.crud.impl.user.IUserService;
import main.com.bsuir.autoservice.service.crud.impl.user.UserService;
import main.com.bsuir.autoservice.service.crud.impl.user_type.IUserTypeService;
import main.com.bsuir.autoservice.service.crud.impl.user_type.UserTypeService;
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
        //get
        bind(GetBeanAddPageCommand.class).in(Singleton.class);
        bind(GetBeanViewPageCommand.class).in(Singleton.class);
        bind(GetBeanMainPageCommand.class).in(Singleton.class);
        bind(GetBeanEditPageCommand.class).in(Singleton.class);
        //delete
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

        bind(IAddressService.class).to(AddressService.class).in(Singleton.class);
        bind(ICarService.class).to(CarService.class).in(Singleton.class);
        bind(ICarStatusService.class).to(CarStatusService.class).in(Singleton.class);
        bind(ICityService.class).to(CityService.class).in(Singleton.class);
        bind(IDriverService.class).to(DriverService.class).in(Singleton.class);
        bind(IDriverCarService.class).to(DriverCarService.class).in(Singleton.class);
        bind(IDriverStatusService.class).to(DriverStatusService.class).in(Singleton.class);
        bind(IOrderService.class).to(OrderService.class).in(Singleton.class);
        bind(IOrderStatusService.class).to(OrderStatusService.class).in(Singleton.class);
        bind(IOrderedProductService.class).to(OrderedProductService.class).in(Singleton.class);
        bind(IPaymentService.class).to(PaymentService.class).in(Singleton.class);
        bind(IPhoneService.class).to(PhoneService.class).in(Singleton.class);
        bind(IPhoneOperatorService.class).to(PhoneOperatorService.class).in(Singleton.class);
        bind(IProductService.class).to(ProductService.class).in(Singleton.class);
        bind(IProductTypeService.class).to(ProductTypeService.class).in(Singleton.class);
        bind(IRegistrationService.class).to(RegistrationService.class).in(Singleton.class);
        bind(IReportService.class).to(ReportService.class).in(Singleton.class);
        bind(IShopService.class).to(ShopService.class).in(Singleton.class);
        bind(IShopProductService.class).to(ShopProductService.class).in(Singleton.class);
        bind(IStaffService.class).to(StaffService.class).in(Singleton.class);
        bind(IStaffPositionService.class).to(StaffPositionService.class).in(Singleton.class);
        bind(IUserService.class).to(UserService.class).in(Singleton.class);
        bind(IUserTypeService.class).to(UserTypeService.class).in(Singleton.class);
    }

    private void bindDao() {
        bind(IDatabase.class).to(SqlDatabase.class).asEagerSingleton();
        bind(ISql.class).to(Sql.class).in(Singleton.class);

        bind(IAddressDao.class).to(AddressDao.class).in(Singleton.class);
        bind(ICarDao.class).to(CarDao.class).in(Singleton.class);
        bind(ICarStatusDao.class).to(CarStatusDao.class).in(Singleton.class);
        bind(ICityDao.class).to(CityDao.class).in(Singleton.class);
        bind(IDriverDao.class).to(DriverDao.class).in(Singleton.class);
        bind(IDriverCarDao.class).to(DriverCarDao.class).in(Singleton.class);
        bind(IDriverStatusDao.class).to(DriverStatusDao.class).in(Singleton.class);
        bind(main.com.bsuir.autoservice.dao.crud.impl.order.IOrderDao.class).to(main.com.bsuir.autoservice.dao.crud.impl.order.OrderDao.class).in(Singleton.class);
        bind(IOrderStatusDao.class).to(OrderStatusDao.class).in(Singleton.class);
        bind(IOrderedProductDao.class).to(OrderedProductDao.class).in(Singleton.class);
        bind(IPaymentDao.class).to(PaymentDao.class).in(Singleton.class);
        bind(IPhoneDao.class).to(PhoneDao.class).in(Singleton.class);
        bind(IPhoneOperatorDao.class).to(PhoneOperatorDao.class).in(Singleton.class);
        bind(IProductDao.class).to(ProductDao.class).in(Singleton.class);
        bind(IProductTypeDao.class).to(ProductTypeDao.class).in(Singleton.class);
        bind(IRegistrationDao.class).to(RegistrationDao.class).in(Singleton.class);
        bind(IReportDao.class).to(ReportDao.class).in(Singleton.class);
        bind(IShopDao.class).to(ShopDao.class).in(Singleton.class);
        bind(IShopProductDao.class).to(ShopProductDao.class).in(Singleton.class);
        bind(IStaffDao.class).to(StaffDao.class).in(Singleton.class);
        bind(IStaffPositionDao.class).to(StaffPositionDao.class).in(Singleton.class);
        bind(IUserDao.class).to(UserDao.class).in(Singleton.class);
        bind(IUserTypeDao.class).to(UserTypeDao.class).in(Singleton.class);
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
