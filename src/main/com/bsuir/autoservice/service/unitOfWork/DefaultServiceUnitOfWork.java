package main.com.bsuir.autoservice.service.unitOfWork;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.order.IOrderService;
import main.com.bsuir.autoservice.service.staff.IStaffService;
import main.com.bsuir.autoservice.service.user.IUserService;

import java.lang.reflect.Field;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {
    private final IUserService userService;
    private final IOrderService orderService;
    private final IStaffService staffService;

    @Inject
    public DefaultServiceUnitOfWork(Injector injector){
        userService = injector.getInstance(IUserService.class);
        orderService = injector.getInstance(IOrderService.class);
        staffService = injector.getInstance(IStaffService.class);
    }

    @Override
    public IServiceCrud getServiceCrudForBean(String name)
            throws ServiceException{
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field: fields) {
                if(field.getName().contains(name)){
                    field.setAccessible(true);
                    return (IServiceCrud) field.get(this);
                }
            }
            throw new ServiceException(String.format("Service not found for user '%s'", name));
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public IOrderService getOrderService() {
        return orderService;
    }

    @Override
    public IStaffService getStaffService(){return staffService;}
}
