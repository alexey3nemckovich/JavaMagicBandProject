package general.service;

import main.com.bsuir.autoservice.service.crud.order.IOrderService;
import main.com.bsuir.autoservice.service.crud.staff.IStaffService;
import main.com.bsuir.autoservice.service.crud.user.IUserService;

import static org.mockito.Mockito.mock;

public class MockService{
    public static IUserService getUserService(){
        return mock(IUserService.class);
    }

    public static IOrderService getOrderService() {
        return mock(IOrderService.class);
    }

    public static IStaffService getStaffService() {
        return mock(IStaffService.class);
    }
}
