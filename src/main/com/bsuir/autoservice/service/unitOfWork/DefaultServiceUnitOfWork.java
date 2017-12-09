package main.com.bsuir.autoservice.service.unitOfWork;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.impl.address.IAddressService;
import main.com.bsuir.autoservice.service.crud.impl.car.ICarService;
import main.com.bsuir.autoservice.service.crud.impl.car_status.ICarStatusService;
import main.com.bsuir.autoservice.service.crud.impl.city.ICityService;
import main.com.bsuir.autoservice.service.crud.impl.driver.IDriverService;
import main.com.bsuir.autoservice.service.crud.impl.driver_car.IDriverCarService;
import main.com.bsuir.autoservice.service.crud.impl.driver_status.IDriverStatusService;
import main.com.bsuir.autoservice.service.crud.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.impl.order_status.IOrderStatusService;
import main.com.bsuir.autoservice.service.crud.impl.ordered_product.IOrderedProductService;
import main.com.bsuir.autoservice.service.crud.impl.payment.IPaymentService;
import main.com.bsuir.autoservice.service.crud.impl.phone.IPhoneService;
import main.com.bsuir.autoservice.service.crud.impl.phone_operator.IPhoneOperatorService;
import main.com.bsuir.autoservice.service.crud.impl.product.IProductService;
import main.com.bsuir.autoservice.service.crud.impl.product_type.IProductTypeService;
import main.com.bsuir.autoservice.service.crud.impl.registration.IRegistrationService;
import main.com.bsuir.autoservice.service.crud.impl.report.IReportService;
import main.com.bsuir.autoservice.service.crud.impl.shop.IShopService;
import main.com.bsuir.autoservice.service.crud.impl.shop_product.IShopProductService;
import main.com.bsuir.autoservice.service.crud.impl.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.impl.staff_position.IStaffPositionService;
import main.com.bsuir.autoservice.service.crud.impl.user.IUserService;
import main.com.bsuir.autoservice.service.crud.impl.user_type.IUserTypeService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DefaultServiceUnitOfWork implements IServiceUnitOfWork {

    @Inject
    public DefaultServiceUnitOfWork(Injector injector){
        baseService = injector.getInstance(IService.class);

        addressService= injector.getInstance(IAddressService.class);
        carService= injector.getInstance(ICarService.class);
        carStatusService= injector.getInstance(ICarStatusService.class);
        cityService= injector.getInstance(ICityService.class);
        driverService= injector.getInstance(IDriverService.class);
        driverCarService= injector.getInstance(IDriverCarService.class);
        driverStatusService= injector.getInstance(IDriverStatusService.class);
        orderService= injector.getInstance(IOrderService.class);
        orderStatusService= injector.getInstance(IOrderStatusService.class);
        orderedProductService= injector.getInstance(IOrderedProductService.class);
        paymentService= injector.getInstance(IPaymentService.class);
        phoneService= injector.getInstance(IPhoneService.class);
        phoneOperatorService= injector.getInstance(IPhoneOperatorService.class);
        productService= injector.getInstance(IProductService.class);
        productTypeService= injector.getInstance(IProductTypeService.class);
        registrationService= injector.getInstance(IRegistrationService.class);
        reportService= injector.getInstance(IReportService.class);
        shopService= injector.getInstance(IShopService.class);
        shopProductService= injector.getInstance(IShopProductService.class);
        staffService= injector.getInstance(IStaffService.class);
        staffPositionService= injector.getInstance(IStaffPositionService.class);
        userService= injector.getInstance(IUserService.class);
        userTypeService= injector.getInstance(IUserTypeService.class);

        allTablesServices = new ArrayList<>();

        allTablesServices.add(addressService);
        allTablesServices.add(carService);
        allTablesServices.add(carStatusService);
        allTablesServices.add(cityService);
        allTablesServices.add(driverService);
        allTablesServices.add(driverCarService);
        allTablesServices.add(driverStatusService);
        allTablesServices.add(orderService);
        allTablesServices.add(orderStatusService);
        allTablesServices.add(orderedProductService);
        allTablesServices.add(paymentService);
        allTablesServices.add(phoneService);
        allTablesServices.add(phoneOperatorService);
        allTablesServices.add(productService);
        allTablesServices.add(productTypeService);
        allTablesServices.add(registrationService);
        allTablesServices.add(reportService);
        allTablesServices.add(shopService);
        allTablesServices.add(shopProductService);
        allTablesServices.add(staffService);
        allTablesServices.add(staffPositionService);
        allTablesServices.add(userService);
        allTablesServices.add(userTypeService);
    }

    @Override
    public List<IServiceCrud> getAllTablesServices(){
        return allTablesServices;
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
    public IAddressService getAddressService() {
        return addressService;
    }

    @Override
    public ICarService getCarService() {
        return carService;
    }

    @Override
    public ICarStatusService getCarStatusService() {
        return carStatusService;
    }

    @Override
    public ICityService getCityService() {
        return cityService;
    }

    @Override
    public IDriverService getDriverService() {
        return driverService;
    }

    @Override
    public IDriverCarService getDriverCarService() {
        return driverCarService;
    }

    @Override
    public IDriverStatusService getDriverStatusService() {
        return driverStatusService;
    }

    @Override
    public IOrderService getOrderService() {
        return orderService;
    }

    @Override
    public IOrderStatusService getOrderStatusService() {
        return orderStatusService;
    }

    @Override
    public IOrderedProductService getOrderedProductService() {
        return orderedProductService;
    }

    @Override
    public IPaymentService getPaymentService() {
        return paymentService;
    }

    @Override
    public IPhoneService getPhoneService() {
        return phoneService;
    }

    @Override
    public IPhoneOperatorService getPhoneOperatorService() {
        return phoneOperatorService;
    }

    @Override
    public IProductService getProductService() {
        return productService;
    }

    @Override
    public IProductTypeService getProductTypeService() {
        return productTypeService;
    }

    @Override
    public IRegistrationService getRegistrationService() {
        return registrationService;
    }

    @Override
    public IReportService getReportService() {
        return reportService;
    }

    @Override
    public IShopService getShopService() {
        return shopService;
    }

    @Override
    public IShopProductService getShopProductService() {
        return shopProductService;
    }

    @Override
    public IStaffService getStaffService() {
        return staffService;
    }

    @Override
    public IStaffPositionService getStaffPositionService() {
        return staffPositionService;
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public IUserTypeService getUserTypeService() {
        return userTypeService;
    }

    private final IService baseService;

    private final IAddressService addressService;
    private final ICarService carService;
    private final ICarStatusService carStatusService;
    private final ICityService cityService;
    private final IDriverService driverService;
    private final IDriverCarService driverCarService;
    private final IDriverStatusService driverStatusService;
    private final IOrderService orderService;
    private final IOrderStatusService orderStatusService;
    private final IOrderedProductService orderedProductService;
    private final IPaymentService paymentService;
    private final IPhoneService phoneService;
    private final IPhoneOperatorService phoneOperatorService;
    private final IProductService productService;
    private final IProductTypeService productTypeService;
    private final IRegistrationService registrationService;
    private final IReportService reportService;
    private final IShopService shopService;
    private final IShopProductService shopProductService;
    private final IStaffService staffService;
    private final IStaffPositionService staffPositionService;
    private final IUserService userService;
    private final IUserTypeService userTypeService;

    private final List<IServiceCrud> allTablesServices;
}
