package unit.command.login;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.impl.Staff;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.login.LoginCommand;
import main.com.bsuir.autoservice.command.param.LoginInfo;
import main.com.bsuir.autoservice.command.ret.LoginRet;
import main.com.bsuir.autoservice.dto.UserStaffDTO;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.user.IUserService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class LoginCommandTest {
    private IUserService userService;
    private LoginCommand loginCommand;

    @Before
    public void beforeTest(){
        userService = getUserSevice();
        IServiceUnitOfWork mockUOF = getServiceUOF(userService);
        IUserSession session = getSession();
        loginCommand = getLoginCommand(mockUOF, session);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static IServiceUnitOfWork getServiceUOF(IUserService service){
        return new MockService.ServiceUOFBuilder().setUserService(service).build();
    }

    private static IUserService getUserSevice(){
        return MockService.getUserService();
    }

    private static final String MOCK_LOGIN  = "test_login";
    private static final String MOCK_PASSWORD = "test_password";

    private static LoginInfo getLoginInfo(){
        LoginInfo loginInfo = mock(LoginInfo.class);
        when(loginInfo.getLogin()).thenReturn(MOCK_LOGIN);
        when(loginInfo.getPassword()).thenReturn(MOCK_PASSWORD);
        return loginInfo;
    }

    private LoginCommand getLoginCommand(IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new LoginCommand(serviceUnitOfWork, session);
    }

    private static LoginRet getTestLoginRet(boolean isAuthorized){
        return new LoginRet(isAuthorized);
    }

    private static final int MOCK_USER_ID = MockBean.MOCK_USER_ID;
    private static final String MOCK_USER_NAME = MockBean.MOCK_USER_NAME;
    private static final Staff.Specialization MOCK_USER_SPECIALIZATION = null;

    private static UserStaffDTO mockUserStaffDTO(){
        return new UserStaffDTO(MOCK_USER_ID, MOCK_USER_NAME, MOCK_USER_SPECIALIZATION);
    }

    @Test
    public void checkLoginSuccess() throws CommandException, ServiceException {
        final boolean verifyCheckLogin = true;
        when(userService.checkLogin(MOCK_LOGIN, MOCK_PASSWORD)).thenReturn(MOCK_USER_ID);
        when(userService.getUserStaffInformation(MOCK_USER_ID)).thenReturn(mockUserStaffDTO());
        assertEquals(loginCommand.execute(getLoginInfo()), getTestLoginRet(verifyCheckLogin));
    }

    @Test(expected = CommandException.class)
    public void checkLoginException() throws ServiceException, CommandException {
        when(userService.checkLogin(anyString(), anyString())).thenThrow(ServiceException.class);
        loginCommand.execute(getLoginInfo());
        fail();
    }
}
