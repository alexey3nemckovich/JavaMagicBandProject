package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.*;

public interface IServiceUnitOfWork {
   IBaseService getBaseService();
   IUserService getUserService();
   IOrderService getOrderService();
   IStaffService getStaffService();
   IServiceCrud getServiceCrudForBean(String name) throws ServiceException;
   IServiceService getServiceService();
   IShareService getShareService();
   INotificationService getNotificationService();
   ISparePartService getSparePartService();
}
