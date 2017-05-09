package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.impl.discount.IDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.discount_user.IDiscountUserService;
import main.com.bsuir.autoservice.service.crud.impl.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.crud.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.order_spare_part.IOrderSparePartService;
import main.com.bsuir.autoservice.service.crud.impl.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service_shop.IServiceShopBeanService;
import main.com.bsuir.autoservice.service.crud.impl.share.IShareService;
import main.com.bsuir.autoservice.service.crud.impl.share_discount.IShareDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.spare_part.ISparePartService;
import main.com.bsuir.autoservice.service.crud.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.impl.user.IUserService;

import java.util.List;

public interface IServiceUnitOfWork {
   IService getBaseService();

   IDiscountService getDiscountService();
   IDiscountUserService getDiscountUserService();
   INotificationService getNotificationService();
   IOrderSparePartService getOrderSparePartService();
   IServiceBeanService getServiceBeanService();
   IServiceShopBeanService getServiceShopBeanService();
   IShareService getShareService();
   IShareDiscountService getShareDiscountService();
   ISparePartService getSparePartService();
   IUserService getUserService();
   IOrderService getOrderService();
   IStaffService getStaffService();

   List<IServiceCrud> getAllTablesServices();
   IServiceCrud getServiceCrudForBean(String name) throws ServiceException;
}
