package general.service;

import main.com.bsuir.autoservice.service.INotificationService;
import main.com.bsuir.autoservice.service.IServiceService;
import main.com.bsuir.autoservice.service.IShareService;
import main.com.bsuir.autoservice.service.crud.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.user.IUserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockService{
    public static class ServiceUOFBuilder{
        private IUserService userService;
        private IOrderService orderService;
        private IStaffService staffService;
        private IServiceService serviceService;
        private IShareService shareService;
        private INotificationService notificationService;

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

        public ServiceUOFBuilder setServiceService(IServiceService serviceService) {
            this.serviceService = serviceService;

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

        public IServiceUnitOfWork build(){
            IServiceUnitOfWork mock = mock(IServiceUnitOfWork.class);
            when(mock.getUserService()).thenReturn(userService);
            when(mock.getStaffService()).thenReturn(staffService);
            when(mock.getOrderService()).thenReturn(orderService);
            when(mock.getServiceService()).thenReturn(serviceService);
            when(mock.getShareService()).thenReturn(shareService);
            when(mock.getNotificationService()).thenReturn(notificationService);
            return mock;
        }
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

    public static IServiceService getServiceService(){return mock(IServiceService.class);}

    public static IShareService getShareService() {
        return mock(IShareService.class);
    }

    public static INotificationService getNotificationService() {
        return mock(INotificationService.class);
    }
}
