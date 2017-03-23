package main.com.bsuir.autoservice.service.unitOfWork.impl;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.service.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.impl.user.IUserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

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
