package main.com.bsuir.autoservice.dao.unitOfWork;

import main.com.bsuir.autoservice.dao.crud.impl.address.IAddressDao;
import main.com.bsuir.autoservice.dao.crud.impl.car.ICarDao;
import main.com.bsuir.autoservice.dao.crud.impl.car_status.ICarStatusDao;
import main.com.bsuir.autoservice.dao.crud.impl.city.ICityDao;
import main.com.bsuir.autoservice.dao.crud.impl.driver.IDriverDao;
import main.com.bsuir.autoservice.dao.crud.impl.driver_car.IDriverCarDao;
import main.com.bsuir.autoservice.dao.crud.impl.driver_status.IDriverStatusDao;
import main.com.bsuir.autoservice.dao.crud.impl.order.IOrderDao;
import main.com.bsuir.autoservice.dao.crud.impl.order_status.IOrderStatusDao;
import main.com.bsuir.autoservice.dao.crud.impl.ordered_product.IOrderedProductDao;
import main.com.bsuir.autoservice.dao.crud.impl.payment.IPaymentDao;
import main.com.bsuir.autoservice.dao.crud.impl.phone.IPhoneDao;
import main.com.bsuir.autoservice.dao.crud.impl.phone_operator.IPhoneOperatorDao;
import main.com.bsuir.autoservice.dao.crud.impl.product.IProductDao;
import main.com.bsuir.autoservice.dao.crud.impl.product_type.IProductTypeDao;
import main.com.bsuir.autoservice.dao.crud.impl.registration.IRegistrationDao;
import main.com.bsuir.autoservice.dao.crud.impl.report.IReportDao;
import main.com.bsuir.autoservice.dao.crud.impl.shop.IShopDao;
import main.com.bsuir.autoservice.dao.crud.impl.shop_product.IShopProductDao;
import main.com.bsuir.autoservice.dao.crud.impl.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.crud.impl.staff_position.IStaffPositionDao;
import main.com.bsuir.autoservice.dao.crud.impl.user.IUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.user_type.IUserTypeDao;

public interface IDaoUnitOfWork {
   IAddressDao getAddressDao();
   ICarDao getCarDao();
   ICarStatusDao getCarStatusDao();
   ICityDao getCityDao();
   IDriverDao getDriverDao();
   IDriverCarDao getDriverCarDao();
   IDriverStatusDao getDriverStatusDao();
   IOrderDao getOrderDao();
   IOrderStatusDao getOrderStatusDao();
   IOrderedProductDao getOrderedProductDao();
   IPaymentDao getPaymentDao();
   IPhoneDao getPhoneDao();
   IPhoneOperatorDao getPhoneOperatorDao();
   IProductDao getProductDao();
   IProductTypeDao getProductTypeDao();
   IRegistrationDao getRegistrationDao();
   IReportDao getReportDao();
   IShopDao getShopDao();
   IShopProductDao getShopProductDao();
   IStaffDao getStaffDao();
   IStaffPositionDao getStaffPositionDao();
   IUserDao getUserDao();
   IUserTypeDao getUserTypeDao();
}
