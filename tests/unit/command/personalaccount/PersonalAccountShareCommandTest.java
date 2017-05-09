package unit.command.personalaccount;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.share;
import main.com.bsuir.autoservice.command.account.PersonalAccountShareCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountShareInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountShareRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.IShareService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonalAccountShareCommandTest {
    private IShareService shareService;
    private PersonalAccountShareCommand personalAccountShareCommand;

    @Before
    public void beforeTest() {
        shareService = getShareService();
        IServiceUnitOfWork mockUOF = getServiceUOF(shareService);
        IUserSession session = getSession();
        personalAccountShareCommand = getPersonalAccountShareCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(IShareService service){
        return new MockService.ServiceUOFBuilder()
                .setShareService(service)
                .build();
    }

    private static IShareService getShareService(){
        return MockService.getShareService();
    }

    private static final int MOCK_USER_ID = MockSession.MOCK_SESSION_ID;

    private static PersonalAccountShareInfo getPersonalAccountShareInfo(){
        return mock(PersonalAccountShareInfo.class);
    }

    private static PersonalAccountShareRet getTestPersonalAccountShareRet(List<share> shares){
        return new PersonalAccountShareRet(shares);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }
    
    private static PersonalAccountShareCommand getPersonalAccountShareCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new PersonalAccountShareCommand(serviceUnitOfWork, session);
    }

    private static List<share> getMockAccountShares(){
                return new ArrayList<share>(){{
                    add(MockBean.getMockShare());
                }};
    }

    @Test
    public void getAccountAvailableShare() throws CommandException, ServiceException {
        List<share> mockAccountShares = getMockAccountShares();
        when(shareService.getActiveAccountShares(MOCK_USER_ID)).thenReturn(mockAccountShares);
        assertEquals(personalAccountShareCommand.execute(getPersonalAccountShareInfo()),
                getTestPersonalAccountShareRet(mockAccountShares));
    }

    @Test(expected = CommandException.class)
    public void getAccountAvailableShareException() throws ServiceException, CommandException {
        when(shareService.getActiveAccountShares(anyInt())).thenThrow(ServiceException.class);
        personalAccountShareCommand.execute(getPersonalAccountShareInfo());
        fail();
    }
}
