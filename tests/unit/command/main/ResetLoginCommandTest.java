package unit.command.main;

import general.service.MockService;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.main.ResetLoginCommand;
import main.com.bsuir.autoservice.command.param.ResetLoginInfo;
import main.com.bsuir.autoservice.command.ret.ResetLoginRet;
import main.com.bsuir.autoservice.service.crud.IUserService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ResetLoginCommandTest {
    private IUserService userService;
    private ResetLoginCommand resetLoginCommand;

    @Before
    public void beforeTest(){
        userService = getUserService();
        IServiceUnitOfWork mockUOF = getServiceUOF(userService);
        resetLoginCommand = getResetLoginCommand(mockUOF);
    }

    private static IServiceUnitOfWork getServiceUOF(IUserService service){
        return new MockService.ServiceUOFBuilder().setUserService(service).build();
    }

    private static IUserService getUserService(){
        return MockService.getUserService();
    }

    private static final String MOCK_EMAIL  = "test_email";

    private static ResetLoginInfo getResetLoginInfo(){
        ResetLoginInfo resetLoginInfo = mock(ResetLoginInfo.class);
        when(resetLoginInfo.getEmail()).thenReturn(MOCK_EMAIL);
        return resetLoginInfo;
    }

    private static ResetLoginRet getTestResetLogin(boolean isResetLogin){
        return new ResetLoginRet(isResetLogin);
    }

    private ResetLoginCommand getResetLoginCommand(IServiceUnitOfWork serviceUnitOfWork){
        return new ResetLoginCommand(serviceUnitOfWork);
    }

    @Test
    public void resetLoginSuccess() throws ServiceException, CommandException {
        final boolean verifyResetLogin = true;
        when(userService.resetLogin(MOCK_EMAIL)).thenReturn(verifyResetLogin);
        assertEquals(resetLoginCommand.execute(getResetLoginInfo()), getTestResetLogin(verifyResetLogin));
    }

    @Test(expected = CommandException.class)
    public void resetLoginException() throws ServiceException, CommandException {
        when(userService.resetLogin(anyString())).thenThrow(ServiceException.class);
        resetLoginCommand.execute(getResetLoginInfo());
        fail();
    }
}
