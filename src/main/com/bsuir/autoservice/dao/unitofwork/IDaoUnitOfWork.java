package main.com.bsuir.autoservice.dao.unitofwork;

import main.com.bsuir.autoservice.dao.impl.discount.IDiscountDao;
import main.com.bsuir.autoservice.dao.impl.discountuser.IDiscountUserDao;
import main.com.bsuir.autoservice.dao.impl.notification.INotificationDao;
import main.com.bsuir.autoservice.dao.impl.order.IOrderDao;
import main.com.bsuir.autoservice.dao.impl.orderedservice.IOrderedServiceDao;
import main.com.bsuir.autoservice.dao.impl.ordersparepart.IOrderSparePartDao;
import main.com.bsuir.autoservice.dao.impl.service.IServiceDao;
import main.com.bsuir.autoservice.dao.impl.serviceshop.IServiceShopDao;
import main.com.bsuir.autoservice.dao.impl.share.IShareDao;
import main.com.bsuir.autoservice.dao.impl.sharediscount.IShareDiscountDao;
import main.com.bsuir.autoservice.dao.impl.sparepart.ISparePartDao;
import main.com.bsuir.autoservice.dao.impl.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.impl.user.IUserDao;

public interface IDaoUnitOfWork {
   IDiscountDao getDiscountDao();
   IDiscountUserDao getDiscountUserDao();
   INotificationDao getNotificationDao();
   IOrderDao getOrderDao();
   IOrderSparePartDao getOrderSparePartDao();
   IOrderedServiceDao getOrderedServiceDao();
   IServiceDao getServiceDao();
   IServiceShopDao getServiceShopDao();
   IShareDao getShareDao();
   IShareDiscountDao getShareDiscountDao();
   ISparePartDao getSparePartDao();
   IStaffDao getStaffDao();
   IUserDao getUserDao();
}
