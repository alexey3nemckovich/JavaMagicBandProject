package unit.command;

import general.bean.MockBean;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.user;
import main.com.bsuir.autoservice.command.account.PersonalAccountInformationCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountInformationRet;
import main.com.bsuir.autoservice.service.INotificationService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.user.IUserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
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
    public void beforeTest(){
        userService = getUserSevice();
        notificationService = getNotificationService();
        IServiceUnitOfWork mockUOF = getServiceUOF(userService, notificationService);
        personalAccountInformationCommand = getPersonalAccountInformationCommand(mockUOF);
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

    private static final boolean MOCK_AUTHORIZED = true;
    private static final int MOCK_USER_ID = 1;

    private static PersonalAccountInformationInfo getPersonalAccountInformationInfo(){
        return mock(PersonalAccountInformationInfo.class);
    }

    private static PersonalAccountInformationInfo getAuthorizedPersonalAccountInformationInfo(){
        PersonalAccountInformationInfo personalAccountInformationInfo = getPersonalAccountInformationInfo();
        when(personalAccountInformationInfo.isAuthorized()).thenReturn(MOCK_AUTHORIZED);
        when(personalAccountInformationInfo.getUserId()).thenReturn(MOCK_USER_ID);
        return personalAccountInformationInfo;
    }

    private static PersonalAccountInformationInfo getUnauthorizedPersonalAccountInformationInfo(){
        PersonalAccountInformationInfo personalAccountInformationInfo = getPersonalAccountInformationInfo();
        when(personalAccountInformationInfo.isAuthorized()).thenReturn(!MOCK_AUTHORIZED);
        return personalAccountInformationInfo;
    }

    private static PersonalAccountInformationRet getUnauthorizedPersonalAccountInformationRet(){
        return new PersonalAccountInformationRet.Builder().setNestedIsContinueWork(!MOCK_AUTHORIZED).build();
    }

    private static PersonalAccountInformationRet getAuthorizedPersonalAccountInformationRet(user user,
                                                                                            boolean haveNotification){
        return new PersonalAccountInformationRet.Builder()
                .setNestedIsContinueWork(MOCK_AUTHORIZED)
                .setNestedGeneralUserInformation(user)
                .setNestedHaveNewNotification(haveNotification)
                .build();
    }

    private static PersonalAccountInformationCommand getPersonalAccountInformationCommand(
            IServiceUnitOfWork serviceUnitOfWork){
        return new PersonalAccountInformationCommand(serviceUnitOfWork);
    }

    private static user getMockUser() {
        return MockBean.getMockUser();
    }


    @Test
    public void checkEnterUnauthorizedClient() throws CommandException, ServiceException {
        assertEquals(personalAccountInformationCommand.execute(
                getUnauthorizedPersonalAccountInformationInfo()),
                getUnauthorizedPersonalAccountInformationRet());
    }

    @Test
    public void checkEnterAuthorizedClient() throws CommandException, ServiceException {
        user mockUser = getMockUser();
        when(userService.getGeneralInformation(MOCK_USER_ID)).thenReturn(mockUser);
        final boolean haveNewNotification = true;
        when(notificationService.haveNewNotification()).thenReturn(haveNewNotification);
        assertEquals(personalAccountInformationCommand.execute(
                getAuthorizedPersonalAccountInformationInfo()),
                getAuthorizedPersonalAccountInformationRet(mockUser, haveNewNotification));
    }

    @Test(expected = CommandException.class)
    public void checkEnterException() throws ServiceException, CommandException {
        when(userService.getGeneralInformation(anyInt())).thenThrow(ServiceException.class);
        when(notificationService.haveNewNotification()).thenThrow(ServiceException.class);
        personalAccountInformationCommand.execute(
                getAuthorizedPersonalAccountInformationInfo()
        );
        fail();
    }
}
