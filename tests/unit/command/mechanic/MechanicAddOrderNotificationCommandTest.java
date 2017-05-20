package unit.command.mechanic;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.mechanic.MechanicAddOrderNotificationCommand;
import main.com.bsuir.autoservice.command.param.MechanicAddOrderNotificationInfo;
import main.com.bsuir.autoservice.command.ret.MechanicAddOrderNotificationRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.IOrderService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MechanicAddOrderNotificationCommandTest {
    private IOrderService orderService;
    private MechanicAddOrderNotificationCommand mechanicAddOrderNotificationCommand;

    @Before
    public void beforeTest() {
        orderService = getOrderService();
        IServiceUnitOfWork mockUOF = getServiceUOF(orderService);
        IUserSession session = getSession();
        mechanicAddOrderNotificationCommand = getMechanicAddOrderNotificationCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(IOrderService orderService){
        return new MockService.ServiceUOFBuilder()
                .setOrderService(orderService)
                .build();
    }

    private static IOrderService getOrderService(){
        return MockService.getOrderService();
    }

    private static MechanicAddOrderNotificationInfo getMechanicAddOrderNotificationInfo(){
        return mock(MechanicAddOrderNotificationInfo.class);
    }

    private static MechanicAddOrderNotificationRet getTestMechanicAddOrderNotificationRet(boolean isAddOrderNotification){
        return new MechanicAddOrderNotificationRet(isAddOrderNotification);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static MechanicAddOrderNotificationCommand getMechanicAddOrderNotificationCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new MechanicAddOrderNotificationCommand(serviceUnitOfWork, session);
    }


    private static final int MOCK_USER_STAFF_ID = MockBean.MOCK_USER_ID;
    private static final int MOCK_ORDER_ID = MockBean.MOCK_ORDER_ID;
    private static final String MOCK_NOTIFICATION_MESSAGE = "Notification_message from staff.";

    @Test
    public void checkAddNewOrderNotification() throws CommandException, ServiceException {
        final boolean verifyIsAddOrderNotification = true;
        when(orderService.addOrderNotification(MOCK_USER_STAFF_ID, MOCK_ORDER_ID, MOCK_NOTIFICATION_MESSAGE)).
                thenReturn(verifyIsAddOrderNotification);
        MechanicAddOrderNotificationInfo mockInfo = getMechanicAddOrderNotificationInfo();
        when(mockInfo.getOrderId()).thenReturn(MOCK_ORDER_ID);
        when(mockInfo.getNotificationMessage()).thenReturn(MOCK_NOTIFICATION_MESSAGE);
        assertEquals(mechanicAddOrderNotificationCommand.execute(mockInfo),
                getTestMechanicAddOrderNotificationRet(verifyIsAddOrderNotification));
    }

    @Test(expected = CommandException.class)
    public void checkAddNewOrderNotificationException() throws ServiceException, CommandException {
        when(orderService.addOrderNotification(anyInt(), anyInt(), anyString())).thenThrow(ServiceException.class);
        mechanicAddOrderNotificationCommand.execute(getMechanicAddOrderNotificationInfo());
        fail();
    }
}
