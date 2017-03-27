package main.com.bsuir.autoservice.service.unitOfWork;

import main.com.bsuir.autoservice.service.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.impl.user.IUserService;

public interface IServiceUnitOfWork {
   IUserService getUserService();
   IOrderService getOrderService();
}
