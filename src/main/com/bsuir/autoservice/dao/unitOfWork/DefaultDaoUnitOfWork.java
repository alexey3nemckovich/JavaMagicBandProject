package main.com.bsuir.autoservice.dao.unitOfWork;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.dao.crud.discount.IDiscountDao;
import main.com.bsuir.autoservice.dao.crud.discount_user.IDiscountUserDao;
import main.com.bsuir.autoservice.dao.crud.notification.INotificationDao;
import main.com.bsuir.autoservice.dao.crud.order.IOrderDao;
import main.com.bsuir.autoservice.dao.crud.order_spare_part.IOrderSparePartDao;
import main.com.bsuir.autoservice.dao.crud.ordered_service.IOrderedServiceDao;
import main.com.bsuir.autoservice.dao.crud.service.IServiceDao;
import main.com.bsuir.autoservice.dao.crud.service_shop.IServiceShopDao;
import main.com.bsuir.autoservice.dao.crud.share.IShareDao;
import main.com.bsuir.autoservice.dao.crud.share_discount.IShareDiscountDao;
import main.com.bsuir.autoservice.dao.crud.spare_part.ISparePartDao;
import main.com.bsuir.autoservice.dao.crud.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.crud.user.IUserDao;

public class DefaultDaoUnitOfWork implements IDaoUnitOfWork {
    private final IDiscountDao discountDao;
    private final IDiscountUserDao discountUserDao;
    private final INotificationDao notificationDao;
    private final IOrderDao orderDao;
    private final IOrderSparePartDao orderSparePartDao;
    private final IOrderedServiceDao orderedServiceDao;
    private final IServiceDao serviceDao;
    private final IServiceShopDao serviceShopDao;
    private final IShareDao shareDao;
    private final IShareDiscountDao shareDiscountDao;
    private final ISparePartDao sparePartDao;
    private final IStaffDao staffDao;
    private final IUserDao userDao;

    @Inject
    public DefaultDaoUnitOfWork(Injector injector) {
        discountDao = injector.getInstance(IDiscountDao.class);
        discountUserDao = injector.getInstance(IDiscountUserDao.class);
        notificationDao = injector.getInstance(INotificationDao.class);
        orderDao = injector.getInstance(IOrderDao.class);
        orderSparePartDao = injector.getInstance(IOrderSparePartDao.class);
        orderedServiceDao = injector.getInstance(IOrderedServiceDao.class);
        serviceDao = injector.getInstance(IServiceDao.class);
        serviceShopDao = injector.getInstance(IServiceShopDao.class);
        shareDao = injector.getInstance(IShareDao.class);
        shareDiscountDao = injector.getInstance(IShareDiscountDao.class);
        sparePartDao = injector.getInstance(ISparePartDao.class);
        staffDao = injector.getInstance(IStaffDao.class);
        userDao = injector.getInstance(IUserDao.class);
    }

    @Override
    public IDiscountDao getDiscountDao(){
        return discountDao;
    }

    @Override
    public IDiscountUserDao getDiscountUserDao(){
        return discountUserDao;
    }

    @Override
    public INotificationDao getNotificationDao(){
        return notificationDao;
    }

    @Override
    public IOrderDao getOrderDao(){
        return orderDao;
    }

    @Override
    public IOrderSparePartDao getOrderSparePartDao(){
        return orderSparePartDao;
    }

    @Override
    public IOrderedServiceDao getOrderedServiceDao(){
        return orderedServiceDao;
    }

    @Override
    public IServiceDao getServiceDao(){
        return serviceDao;
    }

    @Override
    public IServiceShopDao getServiceShopDao(){
        return serviceShopDao;
    }

    @Override
    public IShareDao getShareDao(){
        return shareDao;
    }

    @Override
    public IShareDiscountDao getShareDiscountDao(){
        return shareDiscountDao;
    }

    @Override
    public ISparePartDao getSparePartDao(){
        return sparePartDao;
    }

    @Override
    public IStaffDao getStaffDao(){
        return staffDao;
    }

    @Override
    public IUserDao getUserDao(){
        return userDao;
    }
}
