package main.com.bsuir.autoservice.service.unitOfWork.impl;

import main.com.bsuir.autoservice.service.impl.orderService.IOrderService;
import main.com.bsuir.autoservice.service.impl.userService.IUserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {
    private final IUserService userService;
    private final IOrderService orderService;

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
