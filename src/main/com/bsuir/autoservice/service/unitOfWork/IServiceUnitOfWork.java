package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import main.com.bsuir.autoservice.service.crud.impl.address.IAddressService;
import main.com.bsuir.autoservice.service.crud.impl.car.ICarService;
import main.com.bsuir.autoservice.service.crud.impl.car_status.ICarStatusService;
import main.com.bsuir.autoservice.service.crud.impl.city.ICityService;
import main.com.bsuir.autoservice.service.crud.impl.driver.IDriverService;
import main.com.bsuir.autoservice.service.crud.impl.driver_car.IDriverCarService;
import main.com.bsuir.autoservice.service.crud.impl.driver_status.IDriverStatusService;
import main.com.bsuir.autoservice.service.crud.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.order_status.IOrderStatusService;
import main.com.bsuir.autoservice.service.crud.impl.ordered_product.IOrderedProductService;
import main.com.bsuir.autoservice.service.crud.impl.payment.IPaymentService;
import main.com.bsuir.autoservice.service.crud.impl.phone.IPhoneService;
import main.com.bsuir.autoservice.service.crud.impl.phone_operator.IPhoneOperatorService;
import main.com.bsuir.autoservice.service.crud.impl.product.IProductService;
import main.com.bsuir.autoservice.service.crud.impl.product_type.IProductTypeService;
import main.com.bsuir.autoservice.service.crud.impl.registration.IRegistrationService;
import main.com.bsuir.autoservice.service.crud.impl.report.IReportService;
import main.com.bsuir.autoservice.service.crud.impl.shop.IShopService;
import main.com.bsuir.autoservice.service.crud.impl.shop_product.IShopProductService;
import main.com.bsuir.autoservice.service.crud.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.impl.staff_position.IStaffPositionService;
import main.com.bsuir.autoservice.service.crud.impl.user.IUserService;
import main.com.bsuir.autoservice.service.crud.impl.user_type.IUserTypeService;

import java.util.List;

public interface IServiceUnitOfWork {
   IService getBaseService();

   IAddressService getAddressService();
   ICarService getCarService();
   ICarStatusService getCarStatusService();
   ICityService getCityService();
   IDriverService getDriverService();
   IDriverCarService getDriverCarService();
   IDriverStatusService getDriverStatusService();
   IOrderService getOrderService();
   IOrderStatusService getOrderStatusService();
   IOrderedProductService getOrderedProductService();
   IPaymentService getPaymentService();
   IPhoneService getPhoneService();
   IPhoneOperatorService getPhoneOperatorService();
   IProductService getProductService();
   IProductTypeService getProductTypeService();
   IRegistrationService getRegistrationService();
   IReportService getReportService();
   IShopService getShopService();
   IShopProductService getShopProductService();
   IStaffService getStaffService();
   IStaffPositionService getStaffPositionService();
   IUserService getUserService();
   IUserTypeService getUserTypeService();

   List<IServiceCrud> getAllTablesServices();
   IServiceCrud getServiceCrudForBean(String name) throws ServiceException;
}
