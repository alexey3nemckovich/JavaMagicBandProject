package general.service;

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
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockService{
    public static class ServiceUOFBuilder{
        private IUserService userService;
        private IOrderService orderService;
        private IStaffService staffService;
        private IServiceBeanService serviceBeanService;
        private IShareService shareService;
        private INotificationService notificationService;
        private ISparePartService sparePartService;
        private IBaseService baseService;
        private IDiscountService discountService;
        private IServiceShopBeanService serviceShopBeanService;
        private ICrudService crudService;

        public ServiceUOFBuilder setUserService(IUserService userService) {
            this.userService = userService;

            return this;
        }

        public ServiceUOFBuilder setOrderService(IOrderService orderService) {
            this.orderService = orderService;

            return this;
        }

        public ServiceUOFBuilder setStaffService(IStaffService staffService) {
            this.staffService = staffService;

            return this;
        }

        public ServiceUOFBuilder setServiceBeanService(IServiceBeanService serviceBeanService) {
            this.serviceBeanService = serviceBeanService;

            return this;
        }

        public ServiceUOFBuilder setShareService(IShareService shareService) {
            this.shareService = shareService;

            return this;
        }

        public ServiceUOFBuilder setNotificationService(INotificationService notificationService) {
            this.notificationService = notificationService;

            return this;
        }

        public ServiceUOFBuilder setSparePartService(ISparePartService sparePartService) {
            this.sparePartService = sparePartService;

            return this;
        }

        public ServiceUOFBuilder setBaseService(IBaseService baseService) {
            this.baseService = baseService;

            return this;
        }

        public ServiceUOFBuilder setDiscountService(IDiscountService discountService) {
            this.discountService = discountService;

            return this;
        }

        public ServiceUOFBuilder setServiceShopBeanService(IServiceShopBeanService serviceShopBeanService) {
            this.serviceShopBeanService = serviceShopBeanService;

            return this;
        }

        public ServiceUOFBuilder setCrudService(ICrudService crudService) {
            this.crudService = crudService;

            return this;
        }

        public IServiceUnitOfWork build(){
            IServiceUnitOfWork mock = mock(IServiceUnitOfWork.class);
            when(mock.getUserService()).thenReturn(userService);
            when(mock.getStaffService()).thenReturn(staffService);
            when(mock.getOrderService()).thenReturn(orderService);
            when(mock.getShareService()).thenReturn(shareService);
            when(mock.getNotificationService()).thenReturn(notificationService);
            when(mock.getSparePartService()).thenReturn(sparePartService);
            when(mock.getBaseService()).thenReturn(baseService);
            when(mock.getDiscountService()).thenReturn(discountService);
            when(mock.getServiceBeanService()).thenReturn(serviceBeanService);
            when(mock.getServiceShopBeanService()).thenReturn(serviceShopBeanService);
            when(mock.getCrudService()).thenReturn(crudService);
            return mock;
        }
    }

    public static IBaseService getBaseService() {
        return mock(IBaseService.class);
    }

    public static IDiscountService getDiscountService() {
        return mock(IDiscountService.class);
    }

    public static IServiceShopBeanService getServiceShopBeanService() {
        return mock(IServiceShopBeanService.class);
    }

    public static IUserService getUserService(){
        return mock(IUserService.class);
    }

    public static IOrderService getOrderService() {
        return mock(IOrderService.class);
    }

    public static IStaffService getStaffService() {
        return mock(IStaffService.class);
    }

    public static IServiceBeanService getServiceBeanService(){return mock(IServiceBeanService.class);}

    public static IShareService getShareService() {
        return mock(IShareService.class);
    }

    public static INotificationService getNotificationService() {
        return mock(INotificationService.class);
    }

    public static ISparePartService getSparePartService() {
        return mock(ISparePartService.class);
    }
}
