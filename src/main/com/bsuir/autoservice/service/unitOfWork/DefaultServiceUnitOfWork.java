package main.com.bsuir.autoservice.service.unitOfWork;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Cached;
import main.com.bsuir.autoservice.binding.annotation.FakeUOF;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.*;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.impl.discount.IDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.discount_user.IDiscountUserService;
import main.com.bsuir.autoservice.service.crud.impl.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.crud.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.order_spare_part.IOrderSparePartService;
import main.com.bsuir.autoservice.service.crud.impl.ordered_service.IOrderedServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.crud.impl.service_shop.IServiceShopBeanService;
import main.com.bsuir.autoservice.service.crud.impl.share.IShareService;
import main.com.bsuir.autoservice.service.crud.impl.share_discount.IShareDiscountService;
import main.com.bsuir.autoservice.service.crud.impl.spare_part.ISparePartService;
import main.com.bsuir.autoservice.service.crud.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.impl.user.IUserService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {

    @Inject
    public DefaultServiceUnitOfWork(@FakeUOF IServiceUnitOfWork fakeServiceUOF, IDatabaseMap databaseMap){
        baseService = fakeServiceUOF.getBaseService();
        discountService = fakeServiceUOF.getDiscountService();
        discountUserService = fakeServiceUOF.getDiscountUserService();
        notificationService = fakeServiceUOF.getNotificationService();
        orderService = fakeServiceUOF.getOrderService();
        orderSparePartService = fakeServiceUOF.getOrderSparePartService();
        serviceBeanService = fakeServiceUOF.getServiceBeanService();
        serviceShopBeanService = fakeServiceUOF.getServiceShopBeanService();
        shareService = fakeServiceUOF.getShareService();
        shareDiscountService = fakeServiceUOF.getShareDiscountService();
        sparePartService = fakeServiceUOF.getSparePartService();
        staffService = fakeServiceUOF.getStaffService();
        userService = fakeServiceUOF.getUserService();
        orderedServiceBeanService = fakeServiceUOF.getOrderedServiceBeanService();
    }

    @Cached
    protected Map<String, IServiceCrud> getTableNameCrudServices() {
        final Map<String, Class<? extends IServiceCrud>> tableNameCrudServiceClassMap =
                databaseMap.getShowTableNameServiceCrud();
        final Map<String, IServiceCrud> tableNameCrudServiceMap = new HashMap<>();
        try {
            for (Field field : DefaultServiceUnitOfWork.class.getDeclaredFields()) {
                for (Map.Entry<String, Class<? extends IServiceCrud>> entryTableNameCrudClass :
                        tableNameCrudServiceClassMap.entrySet()) {
                    if (field.getType().isAssignableFrom(entryTableNameCrudClass.getValue())) {
                        tableNameCrudServiceMap.put(entryTableNameCrudClass.getKey(), (IServiceCrud) field.get(this));
                        tableNameCrudServiceClassMap.remove(entryTableNameCrudClass.getKey());
                        break;
                    }
                }
                if (tableNameCrudServiceClassMap.size() == 0){
                    break;
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        assert tableNameCrudServiceClassMap.isEmpty()
                : String.format("Not all databaseMap crud found in '%s'", getClass().getName());

        return tableNameCrudServiceMap;
    }

    @Override
    public IServiceCrud getServiceCrudForBean(String tableName)
            throws ServiceException {
        IServiceCrud serviceCrud = getTableNameCrudServices().get(tableName);
        if (serviceCrud != null) {
            return serviceCrud;
        } else {
            throw new ServiceException(String.format("BaseService isn't found for table '%s'", tableName));
        }
    }

    @Override
    public IOrderedServiceBeanService getOrderedServiceBeanService(){
        return orderedServiceBeanService;
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
    public ISparePartService getSparePartService() {
        return sparePartService;
    }

    @Override
    public IBaseService getBaseService(){return baseService;}

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

    private final IBaseService baseService;
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
    private final IOrderedServiceBeanService orderedServiceBeanService;
}
