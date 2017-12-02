package main.com.bsuir.autoservice.dao.unitOfWork;

import main.com.bsuir.autoservice.dao.crud.impl.backup.discount.IDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.discount_user.IDiscountUserDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.notification.INotificationDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.order.IOrderDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.order_spare_part.IOrderSparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.ordered_service.IOrderedServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.service.IServiceDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.service_shop.IServiceShopDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.share.IShareDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.share_discount.IShareDiscountDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.spare_part.ISparePartDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.crud.impl.backup.user.IUserDao;

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
