package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.order.IOrderService;
import main.com.bsuir.autoservice.service.user.IUserService;

public interface IServiceUnitOfWork {
   IUserService getUserService();
   IOrderService getOrderService();
}
