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

    private static LoginInfo getLoginInfo(){
        LoginInfo loginInfo = mock(LoginInfo.class);
        when(loginInfo.getLogin()).thenReturn("test_login");
        when(loginInfo.getPassword()).thenReturn("test_password");
        return loginInfo;
    }

    private LoginCommand getLoginCommand(IServiceUnitOfWork serviceUnitOfWork){
        return new LoginCommand(serviceUnitOfWork);
    }

    @Test
    public void checkLoginSuccess() throws CommandException, ServiceException {
        boolean verifyCheckLogin = true;
        IUserService userService = getUserSevice();
        when(userService.checkLogin(anyString(), anyString())).thenReturn(verifyCheckLogin);
        IServiceUnitOfWork mockUOF =  getServiceUOF(userService);
        LoginCommand loginCommand = getLoginCommand(mockUOF);
        assertEquals(loginCommand.execute(getLoginInfo()), verifyCheckLogin);
}

    @Test(expected = CommandException.class)
    public void checkLoginException() throws ServiceException, CommandException {
        IUserService userService = getUserSevice();
        when(userService.checkLogin(any(String.class), any(String.class))).thenThrow(ServiceException.class);
        IServiceUnitOfWork mockUOF =  getServiceUOF(userService);
        LoginCommand loginCommand = getLoginCommand(mockUOF);
        loginCommand.execute(getLoginInfo());
    }
}