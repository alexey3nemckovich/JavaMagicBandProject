package main.com.bsuir.autoservice.service.unitOfWork;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.service.order.IOrderService;
import main.com.bsuir.autoservice.service.user.IUserService;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {
    private final IUserService userService;
    private final IOrderService orderService;

    @Inject
    public DefaultServiceUnitOfWork(IUserService userService, IOrderService orderService){
        this.userService = userService;
        this.orderService =orderService;
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public IOrderService getOrderService() {
        return orderService;
    }
}
