package main.com.bsuir.autoservice.service.unitOfWork.impl;

import main.com.bsuir.autoservice.service.impl.userService.IServiceUserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {

    IServiceUserService userService;

    public DefaultServiceUnitOfWork(IServiceUserService userService){
        this.userService = userService;
    }

    @Override
    public IServiceUserService getUserService() {
        return userService;
    }
}
