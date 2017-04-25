package command;

import general.MockGeneral;
import general.service.MockService;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.main.ResetLoginCommand;
import main.com.bsuir.autoservice.command.param.ResetLoginInfo;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.crud.user.IUserService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ResetLoginCommandTest {
    private static IServiceUnitOfWork getServiceUOF(IUserService service){
        IServiceUnitOfWork mock = MockGeneral.getServiceUnitOfWork();
        when(mock.getUserService()).thenReturn(service);
        return mock;
    }

    private static IUserService getUserSevice(){
        return MockService.getUserService();
    }

    private static final String MOCK_EMAIL  = "test_email";

    private static ResetLoginInfo getResetLoginInfo(){
        ResetLoginInfo resetLoginInfo = mock(ResetLoginInfo.class);
        when(resetLoginInfo.getEmail()).thenReturn(MOCK_EMAIL);
        return resetLoginInfo;
    }

    private ResetLoginCommand getResetLoginCommand(IServiceUnitOfWork serviceUnitOfWork){
        return new ResetLoginCommand(serviceUnitOfWork);
    }

    @Test
    public void resetLoginSuccess() throws ServiceException, CommandException {
        boolean verifyResetLogin = true;
        IUserService userService = getUserSevice();
        when(userService.resetLogin(MOCK_EMAIL)).thenReturn(verifyResetLogin);
        IServiceUnitOfWork mockUOF = getServiceUOF(userService);
        ResetLoginCommand resetLoginCommand = getResetLoginCommand(mockUOF);
        assertEquals(resetLoginCommand.execute(getResetLoginInfo()), verifyResetLogin);
    }

    @Test(expected = CommandException.class)
    public void resetLoginException() throws ServiceException, CommandException {
        IUserService userService = getUserSevice();
        when(userService.resetLogin(anyString())).thenThrow(ServiceException.class);
        IServiceUnitOfWork mockUOF =  getServiceUOF(userService);
        ResetLoginCommand resetLoginCommand = getResetLoginCommand(mockUOF);
        resetLoginCommand.execute(getResetLoginInfo());
    }
}
