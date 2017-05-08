package main.com.bsuir.autoservice.service.unitOfWork;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.impl.discount.IDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.discount_user.IDiscountUserService;
import main.com.bsuir.autoservice.service.crud.impl.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.crud.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.order_spare_part.IOrderSparePartService;
import main.com.bsuir.autoservice.service.crud.impl.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service_shop.IServiceShopBeanService;
import main.com.bsuir.autoservice.service.crud.impl.share.IShareService;
import main.com.bsuir.autoservice.service.crud.impl.share_discount.IShareDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.spare_part.ISparePartService;
import main.com.bsuir.autoservice.service.crud.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.impl.user.IUserService;

import java.lang.reflect.Field;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {
    private final IService baseService;

    private final IDiscountService discountService;
    private final IDiscountUserService discountUserService;
    private final INotificationService notificationService;
    private final IOrderService orderService;
    private final IOrderSparePartService orderSparePartService;
    private final IServiceBeanService serviceBeanService;
    private final IServiceShopBeanService serviceShopBeanService;
    private final IShareService shareService;
    private final IShareDiscountService shareDiscountService;
    private final ISparePartService sparePartService;
    private final IStaffService staffService;
    private final IUserService userService;

    @Inject
    public DefaultServiceUnitOfWork(Injector injector){
        baseService = injector.getInstance(IService.class);

        discountService = injector.getInstance(IDiscountService.class);
        discountUserService = injector.getInstance(IDiscountUserService.class);
        notificationService = injector.getInstance(INotificationService.class);
        orderService = injector.getInstance(IOrderService.class);
        orderSparePartService = injector.getInstance(IOrderSparePartService.class);
        serviceBeanService = injector.getInstance(IServiceBeanService.class);
        serviceShopBeanService = injector.getInstance(IServiceShopBeanService.class);
        shareService = injector.getInstance(IShareService.class);
        shareDiscountService = injector.getInstance(IShareDiscountService.class);
        sparePartService = injector.getInstance(ISparePartService.class);
        staffService = injector.getInstance(IStaffService.class);
        userService = injector.getInstance(IUserService.class);
    }

    @Override
    public IServiceCrud getServiceCrudForBean(String tableName)
            throws ServiceException{
        try {
            //horror[needs norm binding]
            StringBuilder stringBuilder = new StringBuilder(tableName);
            while (stringBuilder.indexOf("_") != -1){
                int pos = stringBuilder.indexOf("_");
                stringBuilder.setCharAt(
                        pos+1,
                        Character.toUpperCase(stringBuilder.charAt(pos+1))
                );
                stringBuilder.replace(pos, pos+1, "");
            }
            tableName = stringBuilder.toString();
            //horror upper
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
    public IDiscountService getDiscountService(){
        return discountService;
    }

    @Override
    public IDiscountUserService getDiscountUserService(){
        return discountUserService;
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
    public IOrderSparePartService getOrderSparePartService(){
        return orderSparePartService;
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
    public IShareDiscountService getShareDiscountService(){
        return shareDiscountService;
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
    public IUserService getUserService(){
        return userService;
    }
}
