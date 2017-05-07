package main.com.bsuir.autoservice.service.unitOfWork;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Cached;
import main.com.bsuir.autoservice.binding.annotation.FakeUOF;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.IBaseService;
import main.com.bsuir.autoservice.service.INotificationService;
import main.com.bsuir.autoservice.service.IServiceService;
import main.com.bsuir.autoservice.service.IShareService;
import main.com.bsuir.autoservice.service.crud.IOrderService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.IStaffService;
import main.com.bsuir.autoservice.service.crud.IUserService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {
    private final IDatabaseMap databaseMap;
    private final IBaseService baseService;
    private final IUserService userService;
    private final IOrderService orderService;
    private final IStaffService staffService;
    private final IShareService shareService;
    private final IServiceService serviceService;
    private final INotificationService notificationService;

    @Inject
    public DefaultServiceUnitOfWork(@FakeUOF IServiceUnitOfWork fakeServiceUOF, IDatabaseMap databaseMap){
        this.databaseMap = databaseMap;
        baseService = fakeServiceUOF.getBaseService();
        userService = fakeServiceUOF.getUserService();
        orderService = fakeServiceUOF.getOrderService();
        staffService = fakeServiceUOF.getStaffService();
        shareService = fakeServiceUOF.getShareService();
        serviceService = fakeServiceUOF.getServiceService();
        notificationService = fakeServiceUOF.getNotificationService();
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
