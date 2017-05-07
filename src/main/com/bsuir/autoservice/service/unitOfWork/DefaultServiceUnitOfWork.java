package main.com.bsuir.autoservice.service.unitOfWork;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.FakeUOF;
import main.com.bsuir.autoservice.service.IBaseService;
import main.com.bsuir.autoservice.service.INotificationService;
import main.com.bsuir.autoservice.service.IServiceService;
import main.com.bsuir.autoservice.service.IShareService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.user.IUserService;

import java.lang.reflect.Field;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {
    private final IBaseService baseService;
    private final IUserService userService;
    private final IOrderService orderService;
    private final IStaffService staffService;
    private final IShareService shareService;
    private final IServiceService serviceService;
    private final INotificationService notificationService;

    @Inject
    public DefaultServiceUnitOfWork(@FakeUOF IServiceUnitOfWork fakeServiceUOF){
        baseService = fakeServiceUOF.getBaseService();
        userService = fakeServiceUOF.getUserService();
        orderService = fakeServiceUOF.getOrderService();
        staffService = fakeServiceUOF.getStaffService();
        shareService = fakeServiceUOF.getShareService();
        serviceService = fakeServiceUOF.getServiceService();
        notificationService = fakeServiceUOF.getNotificationService();
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
            throw new ServiceException(String.format("BaseService isn't found for table '%s'", tableName));
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public IServiceService getServiceService() {
        return serviceService;
    }

    @Override
    public IShareService getShareService() {
        return shareService;
    }

    @Override
    public INotificationService getNotificationService() {
        return notificationService;
    }

    @Override
    public IBaseService getBaseService(){return baseService;}

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
