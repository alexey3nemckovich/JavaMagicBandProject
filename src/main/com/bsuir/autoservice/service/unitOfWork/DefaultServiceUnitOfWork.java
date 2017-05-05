package main.com.bsuir.autoservice.service.unitOfWork;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.impl.user.IUserService;

import java.lang.reflect.Field;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {
    private final IService baseService;

    private final IUserService userService;
    private final IOrderService orderService;
    private final IStaffService staffService;

    @Inject
    public DefaultServiceUnitOfWork(Injector injector){
        baseService = injector.getInstance(IService.class);
        userService = injector.getInstance(IUserService.class);
        orderService = injector.getInstance(IOrderService.class);
        staffService = injector.getInstance(IStaffService.class);
    }

    @Override
    public IServiceCrud getServiceCrudForBean(String tableName)
            throws ServiceException{
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field: fields) {
                if(field.getName().contains(tableName)){
                    field.setAccessible(true);
                    return (IServiceCrud) field.get(this);
                }
            }
            throw new ServiceException(String.format("BaseService not found for user '%s'", tableName));
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public IService getBaseService(){return baseService;}

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
