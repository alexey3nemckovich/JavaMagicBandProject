package unit.command;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.command.account.PersonalAccountUpdateGeneralInformationCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountUpdateGeneralInformationInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountUpdateGeneralInformationRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.INotificationService;
import main.com.bsuir.autoservice.service.crud.IUserService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
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

    private static User getMockUser() {
        return MockBean.getMockUser();
    }

    @Test
    public void checkUpdateUserInformation() throws CommandException, ServiceException {
        User mockUser = getMockUser();
        final boolean verifyUpdateUser = true;
        when(userService.updateUserInformation(MOCK_USER_ID, mockUser)).thenReturn(verifyUpdateUser);
        PersonalAccountUpdateGeneralInformationInfo mockInfo = getPersonalAccountUpdateGeneralInformationInfo();
        when(mockInfo.getNewUser()).thenReturn(mockUser);
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
