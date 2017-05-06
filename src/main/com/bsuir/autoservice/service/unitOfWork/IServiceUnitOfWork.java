package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.IBaseService;
import main.com.bsuir.autoservice.service.INotificationService;
import main.com.bsuir.autoservice.service.IServiceService;
import main.com.bsuir.autoservice.service.IShareService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.user.IUserService;

public interface IServiceUnitOfWork {
   IBaseService getBaseService();
   IUserService getUserService();
   IOrderService getOrderService();
   IStaffService getStaffService();
   IServiceCrud getServiceCrudForBean(String name) throws ServiceException;
   IServiceService getServiceService();
   IShareService getShareService();
   INotificationService getNotificationService();
}
