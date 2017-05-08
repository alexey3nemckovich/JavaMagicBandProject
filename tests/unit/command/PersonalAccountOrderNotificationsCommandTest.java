package unit.command;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.notification;
import main.com.bsuir.autoservice.command.account.PersonalAccountOrderNotificationsCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountOrderNotificationsInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountOrderNotificationsRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.INotificationService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonalAccountOrderNotificationsCommandTest {
    private INotificationService notificationService;
    private PersonalAccountOrderNotificationsCommand personalAccountOrderNotificationsCommand;

    @Before
    public void beforeTest() {
        notificationService = getNotificationService();
        IServiceUnitOfWork mockUOF = getServiceUOF(notificationService);
        IUserSession session = getSession();
        personalAccountOrderNotificationsCommand = getPersonalAccountOrderNotificationsCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(INotificationService service){
        return new MockService.ServiceUOFBuilder()
                .setNotificationService(service)
                .build();
    }

    private static INotificationService getNotificationService(){
        return MockService.getNotificationService();
    }

    private static PersonalAccountOrderNotificationsInfo getPersonalAccountOrderNotificationsInfo(){
        return mock(PersonalAccountOrderNotificationsInfo.class);
    }

    private static PersonalAccountOrderNotificationsRet getTestPersonalAccountOrderNotificationsRet(
            List<notification> notifications){
        return new PersonalAccountOrderNotificationsRet(notifications);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static PersonalAccountOrderNotificationsCommand getPersonalAccountOrderNotificationsCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new PersonalAccountOrderNotificationsCommand(serviceUnitOfWork, session);
    }

    private static List<notification> getMockOrderNotifications(){
        return new ArrayList<notification>(){{
            add(MockBean.getMockNotification());
        }};
    }

    private static final int MOCK_ORDER_ID = MockBean.MOCK_ORDER_ID;
    private static final int MOCK_USER_ID = MockBean.MOCK_USER_ID;

    @Test
    public void checkGetOrderNotificationsGroup() throws CommandException, ServiceException {
        List<notification> mockOrderNotifications = getMockOrderNotifications();
        when(notificationService.getOrderNotifications(eq(MOCK_USER_ID), eq(MOCK_ORDER_ID), anyInt(), anyInt())).
                thenReturn(mockOrderNotifications);
        PersonalAccountOrderNotificationsInfo mockInfo = getPersonalAccountOrderNotificationsInfo();
        when(mockInfo.getOrderId()).thenReturn(MOCK_ORDER_ID);
        assertEquals(personalAccountOrderNotificationsCommand.execute(mockInfo),
                getTestPersonalAccountOrderNotificationsRet(mockOrderNotifications));
    }

    @Test(expected = CommandException.class)
    public void checkGetOrderNotificationsGroupException() throws ServiceException, CommandException {
        when(notificationService.getOrderNotifications(anyInt(), anyInt(), anyInt(), anyInt())).
                thenThrow(ServiceException.class);
        personalAccountOrderNotificationsCommand.execute(getPersonalAccountOrderNotificationsInfo());
        fail();
    }
}
