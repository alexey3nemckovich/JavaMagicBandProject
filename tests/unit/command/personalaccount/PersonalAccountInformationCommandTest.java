package unit.command.personalaccount;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.command.account.PersonalAccountInformationCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountInformationRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.notifiaction.INotificationService;
import main.com.bsuir.autoservice.service.impl.user.IUserService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonalAccountInformationCommandTest {
    private IUserService userService;
    private INotificationService notificationService;
    private PersonalAccountInformationCommand personalAccountInformationCommand;

    @Before
    public void beforeTest() {
        userService = getUserSevice();
        notificationService = getNotificationService();
        IServiceUnitOfWork mockUOF = getServiceUOF(userService, notificationService);
        IUserSession session = getSession();
        personalAccountInformationCommand = getPersonalAccountInformationCommand(mockUOF, session);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static IServiceUnitOfWork getServiceUOF(IUserService userService, INotificationService notificationService){
        return new MockService.ServiceUOFBuilder()
                .setUserService(userService)
                .setNotificationService(notificationService)
                .build();
    }

    private static IUserService getUserSevice(){
        return MockService.getUserService();
    }

    private static INotificationService getNotificationService(){
        return MockService.getNotificationService();
    }

    private static final int MOCK_USER_ID = MockBean.MOCK_USER_ID;

    private static PersonalAccountInformationInfo getPersonalAccountInformationInfo(){
        return mock(PersonalAccountInformationInfo.class);
    }

    private static PersonalAccountInformationRet getAuthorizedPersonalAccountInformationRet(User user,
                                                                                            boolean haveNotification){
        return new PersonalAccountInformationRet(user, haveNotification);
    }

    private static PersonalAccountInformationCommand getPersonalAccountInformationCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new PersonalAccountInformationCommand(serviceUnitOfWork, session);
    }

    private static User getMockUser() {
        return MockBean.getMockUser();
    }

    @Test
    public void checkEnterAuthorizedClient() throws CommandException, ServiceException {
        User mockUser = getMockUser();
        when(userService.getGeneralInformation(MOCK_USER_ID)).thenReturn(mockUser);
        final boolean haveNewNotification = true;
        when(notificationService.haveNewNotification()).thenReturn(haveNewNotification);
        assertEquals(personalAccountInformationCommand.execute(
                getPersonalAccountInformationInfo()),
                getAuthorizedPersonalAccountInformationRet(mockUser, haveNewNotification));
    }

    @Test(expected = CommandException.class)
    public void checkEnterException() throws ServiceException, CommandException {
        when(userService.getGeneralInformation(anyInt())).thenThrow(ServiceException.class);
        when(notificationService.haveNewNotification()).thenThrow(ServiceException.class);
        personalAccountInformationCommand.execute(
                getPersonalAccountInformationInfo()
        );
        fail();
    }
}
