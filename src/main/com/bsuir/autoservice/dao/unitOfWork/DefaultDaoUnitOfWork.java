package main.com.bsuir.autoservice.dao.unitOfWork;

import com.google.inject.Inject;
import com.google.inject.Injector;
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

public class DefaultDaoUnitOfWork implements IDaoUnitOfWork {

    private final IAddressDao addressDao;
    private final ICarDao carDao;
    private final ICarStatusDao carStatusDao;
    private final ICityDao cityDao;
    private final IDriverDao driverDao;
    private final IDriverCarDao driverCarDao;
    private final IDriverStatusDao driverStatusDao;
    private final IOrderDao orderDao;
    private final IOrderStatusDao orderStatusDao;
    private final IOrderedProductDao orderedProductDao;
    private final IPaymentDao paymentDao;
    private final IPhoneDao phoneDao;
    private final IPhoneOperatorDao phoneOperatorDao;
    private final IProductDao productDao;
    private final IProductTypeDao productTypeDao;
    private final IRegistrationDao registrationDao;
    private final IReportDao reportDao;
    private final IShopDao shopDao;
    private final IShopProductDao shopProductDao;
    private final IStaffDao staffDao;
    private final IStaffPositionDao staffPositionDao;
    private final IUserDao userDao;
    private final IUserTypeDao userTypeDao;

    @Inject
    public DefaultDaoUnitOfWork(Injector injector) {
        addressDao = injector.getInstance(IAddressDao.class);
        carDao = injector.getInstance(ICarDao.class);
        carStatusDao = injector.getInstance(ICarStatusDao.class);
        cityDao = injector.getInstance(ICityDao.class);
        driverDao = injector.getInstance(IDriverDao.class);
        driverCarDao = injector.getInstance(IDriverCarDao.class);
        driverStatusDao = injector.getInstance(IDriverStatusDao.class);
        orderDao = injector.getInstance(IOrderDao.class);
        orderStatusDao = injector.getInstance(IOrderStatusDao.class);
        orderedProductDao = injector.getInstance(IOrderedProductDao.class);
        paymentDao = injector.getInstance(IPaymentDao.class);
        phoneDao = injector.getInstance(IPhoneDao.class);
        phoneOperatorDao = injector.getInstance(IPhoneOperatorDao.class);
        productDao = injector.getInstance(IProductDao.class);
        productTypeDao = injector.getInstance(IProductTypeDao.class);
        registrationDao = injector.getInstance(IRegistrationDao.class);
        reportDao = injector.getInstance(IReportDao.class);
        shopDao = injector.getInstance(IShopDao.class);
        shopProductDao = injector.getInstance(IShopProductDao.class);
        staffDao = injector.getInstance(IStaffDao.class);
        staffPositionDao = injector.getInstance(IStaffPositionDao.class);
        userDao = injector.getInstance(IUserDao.class);
        userTypeDao = injector.getInstance(IUserTypeDao.class);
    }

    @Override
    public IAddressDao getAddressDao() {
        return addressDao;
    }

    @Override
    public ICarDao getCarDao() {
        return carDao;
    }

    @Override
    public ICarStatusDao getCarStatusDao() {
        return carStatusDao;
    }

    @Override
    public ICityDao getCityDao() {
        return cityDao;
    }

    @Override
    public IDriverDao getDriverDao() {
        return driverDao;
    }

    @Override
    public IDriverCarDao getDriverCarDao() {
        return driverCarDao;
    }

    @Override
    public IDriverStatusDao getDriverStatusDao() {
        return driverStatusDao;
    }

    @Override
    public IOrderDao getOrderDao() {
        return orderDao;
    }

    @Override
    public IOrderStatusDao getOrderStatusDao() {
        return orderStatusDao;
    }

    @Override
    public IOrderedProductDao getOrderedProductDao() {
        return orderedProductDao;
    }

    @Override
    public IPaymentDao getPaymentDao() {
        return paymentDao;
    }

    @Override
    public IPhoneDao getPhoneDao() {
        return phoneDao;
    }

    @Override
    public IPhoneOperatorDao getPhoneOperatorDao() {
        return phoneOperatorDao;
    }

    @Override
    public IProductDao getProductDao() {
        return productDao;
    }

    @Override
    public IProductTypeDao getProductTypeDao() {
        return productTypeDao;
    }

    @Override
    public IRegistrationDao getRegistrationDao() {
        return registrationDao;
    }

    @Override
    public IReportDao getReportDao() {
        return reportDao;
    }

    @Override
    public IShopDao getShopDao() {
        return shopDao;
    }

    @Override
    public IShopProductDao getShopProductDao() {
        return shopProductDao;
    }

    @Override
    public IStaffDao getStaffDao() {
        return staffDao;
    }

    @Override
    public IStaffPositionDao getStaffPositionDao() {
        return staffPositionDao;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public IUserTypeDao getUserTypeDao() {
        return userTypeDao;
    }

}
