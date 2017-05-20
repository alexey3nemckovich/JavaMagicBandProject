package main.com.bsuir.autoservice.service.unitofwork;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.FakeUOF;
import main.com.bsuir.autoservice.service.impl.baseservice.IBaseService;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.impl.discount.IDiscountService;
import main.com.bsuir.autoservice.service.impl.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.impl.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.impl.serviceshop.IServiceShopBeanService;
import main.com.bsuir.autoservice.service.impl.share.IShareService;
import main.com.bsuir.autoservice.service.impl.sparepart.ISparePartService;
import main.com.bsuir.autoservice.service.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.impl.user.IUserService;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {

    @Inject
    public DefaultServiceUnitOfWork(@FakeUOF IServiceUnitOfWork fakeServiceUOF){
        baseService = fakeServiceUOF.getBaseService();
        discountService = fakeServiceUOF.getDiscountService();
        notificationService = fakeServiceUOF.getNotificationService();
        orderService = fakeServiceUOF.getOrderService();
        serviceBeanService = fakeServiceUOF.getServiceBeanService();
        serviceShopBeanService = fakeServiceUOF.getServiceShopBeanService();
        shareService = fakeServiceUOF.getShareService();
        sparePartService = fakeServiceUOF.getSparePartService();
        staffService = fakeServiceUOF.getStaffService();
        userService = fakeServiceUOF.getUserService();
        crudService = fakeServiceUOF.getCrudService();
    }

    @Override
    public IBaseService getBaseService(){return baseService;}

    @Override
    public IDiscountService getDiscountService(){
        return discountService;
    }

    @Override
    public INotificationService getNotificationService(){
        return notificationService;
    }

    @Override
    public IOrderService getOrderService() {
        return orderService;
    }

    @Override
    public IServiceBeanService getServiceBeanService(){
        return serviceBeanService;
    }

    @Override
    public IServiceShopBeanService getServiceShopBeanService(){
        return serviceShopBeanService;
    }

    @Override
    public IShareService getShareService(){
        return shareService;
    }

    @Override
    public ISparePartService getSparePartService(){
        return sparePartService;
    }

    @Override
    public IStaffService getStaffService(){
        return staffService;
    }

    @Override
    public ICrudService getCrudService() {
        return crudService;
    }

    @Override
    public IUserService getUserService(){
        return userService;
    }

    private final IBaseService baseService;
    private final IDiscountService discountService;
    private final INotificationService notificationService;
    private final IOrderService orderService;
    private final IServiceBeanService serviceBeanService;
    private final IServiceShopBeanService serviceShopBeanService;
    private final IShareService shareService;
    private final ISparePartService sparePartService;
    private final IStaffService staffService;
    private final IUserService userService;
    private final ICrudService crudService;
}
