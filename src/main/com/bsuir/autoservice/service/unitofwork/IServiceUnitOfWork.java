package main.com.bsuir.autoservice.service.unitofwork;

import main.com.bsuir.autoservice.service.impl.baseservice.IBaseService;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.impl.discount.IDiscountService;
import main.com.bsuir.autoservice.service.impl.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.impl.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.impl.serviceshop.IServiceShopBeanService;
import main.com.bsuir.autoservice.service.impl.share.IShareService;
import main.com.bsuir.autoservice.service.impl.sparepart.ISparePartService;
import main.com.bsuir.autoservice.service.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.impl.user.IUserService;

public interface IServiceUnitOfWork {
   IBaseService getBaseService();
   IDiscountService getDiscountService();
   INotificationService getNotificationService();
   IServiceBeanService getServiceBeanService();
   IServiceShopBeanService getServiceShopBeanService();
   IShareService getShareService();
   ISparePartService getSparePartService();
   IUserService getUserService();
   IOrderService getOrderService();
   IStaffService getStaffService();
   ICrudService getCrudService();
}
