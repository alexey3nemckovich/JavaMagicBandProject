package main.com.bsuir.autoservice.dao.unitOfWork;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.FakeUOF;
import main.com.bsuir.autoservice.dao.crud.impl.discount.IDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.discount_user.IDiscountUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.notification.INotificationDao;
import main.com.bsuir.autoservice.dao.crud.impl.order.IOrderDao;
import main.com.bsuir.autoservice.dao.crud.impl.order_spare_part.IOrderSparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.ordered_service.IOrderedServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.service.IServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.service_shop.IServiceShopDao;
import main.com.bsuir.autoservice.dao.crud.impl.share.IShareDao;
import main.com.bsuir.autoservice.dao.crud.impl.share_discount.IShareDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.spare_part.ISparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.crud.impl.user.IUserDao;

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
    public DefaultDaoUnitOfWork(@FakeUOF IDaoUnitOfWork fakeDaoUOF) {
        userDao = fakeDaoUOF.getUserDao();
        orderDao = fakeDaoUOF.getOrderDao();
        staffDao = fakeDaoUOF.getStaffDao();
        discountDao = fakeDaoUOF.getDiscountDao();
        discountUserDao = fakeDaoUOF.getDiscountUserDao();
        notificationDao = fakeDaoUOF.getNotificationDao();
        orderSparePartDao = fakeDaoUOF.getOrderSparePartDao();
        orderedServiceDao = fakeDaoUOF.getOrderedServiceDao();
        serviceDao = fakeDaoUOF.getServiceDao();
        serviceShopDao = fakeDaoUOF.getServiceShopDao();
        shareDao = fakeDaoUOF.getShareDao();
        shareDiscountDao = fakeDaoUOF.getShareDiscountDao();
        sparePartDao = fakeDaoUOF.getSparePartDao();
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
