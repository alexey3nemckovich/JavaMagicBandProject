package main.com.bsuir.autoservice.dao.unitOfWork;

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
