package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.order.IOrderService;
import main.com.bsuir.autoservice.service.staff.IStaffService;
import main.com.bsuir.autoservice.service.user.IUserService;

public interface IServiceUnitOfWork {
   IUserService getUserService();
   IOrderService getOrderService();
   IStaffService getStaffService();
   IServiceCrud getServiceCrudForBean(String name) throws ServiceException;
}
