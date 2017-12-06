package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.impl.backup.discount.IDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.backup.discount_user.IDiscountUserService;
import main.com.bsuir.autoservice.service.crud.impl.backup.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.crud.impl.backup.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.backup.order_spare_part.IOrderSparePartService;
import main.com.bsuir.autoservice.service.crud.impl.backup.ordered_service.IOrderedServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.backup.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.backup.service_shop.IServiceShopBeanService;
import main.com.bsuir.autoservice.service.crud.impl.backup.share.IShareService;
import main.com.bsuir.autoservice.service.crud.impl.backup.share_discount.IShareDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.backup.spare_part.ISparePartService;
import main.com.bsuir.autoservice.service.crud.impl.backup.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.impl.backup.user.IUserService;

import java.util.List;

public interface IServiceUnitOfWork {
   IService getBaseService();

   IDiscountService getDiscountService();
   IDiscountUserService getDiscountUserService();
   INotificationService getNotificationService();
   IOrderSparePartService getOrderSparePartService();
   IOrderedServiceBeanService getOrderedServiceBeanService();
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
