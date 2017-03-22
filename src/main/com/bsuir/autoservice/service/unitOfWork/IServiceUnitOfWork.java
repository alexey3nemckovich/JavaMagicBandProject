package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.impl.orderService.IOrderService;
import main.com.bsuir.autoservice.service.impl.userService.IUserService;

public interface IServiceUnitOfWork {
   IUserService getUserService();
   IOrderService getOrderService();
}
