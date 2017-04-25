package command;

import general.MockGeneral;
import general.service.MockService;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.main.LoginCommand;
import main.com.bsuir.autoservice.command.param.LoginInfo;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.user.IUserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LoginCommandTest {
    private static IServiceUnitOfWork getServiceUOF(IUserService service){
        IServiceUnitOfWork mock = MockGeneral.getServiceUnitOfWork();
        when(mock.getUserService()).thenReturn(service);
        return mock;
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

    private LoginCommand getLoginCommand(IServiceUnitOfWork serviceUnitOfWork){
        return new LoginCommand(serviceUnitOfWork);
    }

    @Test
    public void checkLoginSuccess() throws CommandException, ServiceException {
        boolean verifyCheckLogin = true;
        IUserService userService = getUserSevice();
        when(userService.checkLogin(MOCK_LOGIN, MOCK_PASSWORD)).thenReturn(verifyCheckLogin);
        IServiceUnitOfWork mockUOF = getServiceUOF(userService);
        LoginCommand loginCommand = getLoginCommand(mockUOF);
        assertEquals(loginCommand.execute(getLoginInfo()), verifyCheckLogin);
    }

    @Test(expected = CommandException.class)
    public void checkLoginException() throws ServiceException, CommandException {
        IUserService userService = getUserSevice();
        when(userService.checkLogin(anyString(), anyString())).thenThrow(ServiceException.class);
        IServiceUnitOfWork mockUOF =  getServiceUOF(userService);
        LoginCommand loginCommand = getLoginCommand(mockUOF);
        loginCommand.execute(getLoginInfo());
    }
}