package main.com.bsuir.autoservice.dao.unitOfWork;

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
