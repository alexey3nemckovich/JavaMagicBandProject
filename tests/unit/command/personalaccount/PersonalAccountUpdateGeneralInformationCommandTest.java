package unit.command.personalaccount;

import general.bean.MockBean;
import general.dto.MockDTO;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.command.account.PersonalAccountUpdateGeneralInformationCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountUpdateGeneralInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountUpdateGeneralInformationRet;
import main.com.bsuir.autoservice.dto.UserUpdateInformationDTO;
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
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonalAccountUpdateGeneralInformationCommandTest {
    private IUserService userService;
    private PersonalAccountUpdateGeneralInformationCommand personalAccountUpdateGeneralInformationCommand;

    @Before
    public void beforeTest() {
        userService = getUserSevice();
        IServiceUnitOfWork mockUOF = getServiceUOF(userService);
        IUserSession session = getSession();
        personalAccountUpdateGeneralInformationCommand = getPersonalAccountUpdateGeneralInformationCommand(mockUOF, session);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static IServiceUnitOfWork getServiceUOF(IUserService userService){
        return new MockService.ServiceUOFBuilder()
                .setUserService(userService)
                .build();
    }

    private static IUserService getUserSevice(){
        return MockService.getUserService();
    }

    private static INotificationService getNotificationService(){
        return MockService.getNotificationService();
    }

    private static final int MOCK_USER_ID = MockBean.MOCK_USER_ID;

    private static PersonalAccountUpdateGeneralInformationInfo getPersonalAccountUpdateGeneralInformationInfo(){
        return mock(PersonalAccountUpdateGeneralInformationInfo.class);
    }

    private static PersonalAccountUpdateGeneralInformationRet getTestPersonalAccountUpdateGeneralInformationRet(
            boolean isUpdateUser){
        return new PersonalAccountUpdateGeneralInformationRet(isUpdateUser);
    }

    private static PersonalAccountUpdateGeneralInformationCommand getPersonalAccountUpdateGeneralInformationCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new PersonalAccountUpdateGeneralInformationCommand(serviceUnitOfWork, session);
    }

    private static UserUpdateInformationDTO getMockUserUpdateInformation() {
        return MockDTO.getMockUserUpdateInformationDTO();
    }

    private static final String MOCK_USER_NAME = MockDTO.MOCK_USER_NAME;
    private static final String MOCK_USER_LAST_NAME = MockDTO.MOCK_USER_LAST_NAME;
    private static final String MOCK_USER_PHONE = MockDTO.MOCK_USER_PHONE;


    @Test
    public void checkUpdateUserInformation() throws CommandException, ServiceException {
        UserUpdateInformationDTO mockUser = getMockUserUpdateInformation();
        final boolean verifyUpdateUser = true;
        when(userService.updateUserInformation(MOCK_USER_ID, mockUser)).thenReturn(verifyUpdateUser);
        PersonalAccountUpdateGeneralInformationInfo mockInfo = getPersonalAccountUpdateGeneralInformationInfo();
        when(mockInfo.getName()).thenReturn(MOCK_USER_NAME);
        when(mockInfo.getLastName()).thenReturn(MOCK_USER_LAST_NAME);
        when(mockInfo.getPhone()).thenReturn(MOCK_USER_PHONE);

        assertEquals(personalAccountUpdateGeneralInformationCommand.execute(mockInfo),
                getTestPersonalAccountUpdateGeneralInformationRet(verifyUpdateUser));
    }

    @Test(expected = CommandException.class)
    public void checkUpdateUserInformationException() throws ServiceException, CommandException {
        when(userService.updateUserInformation(anyInt(), anyObject())).thenThrow(ServiceException.class);
        personalAccountUpdateGeneralInformationCommand.execute(
                getPersonalAccountUpdateGeneralInformationInfo()
        );
        fail();
    }
}
