package unit.command.personalaccount;

import general.bean.MockBean;
import general.dto.MockDTO;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.impl.Share;
import main.com.bsuir.autoservice.command.account.PersonalAccountAvailableOrderServicesCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountAvailableOrderServicesInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountAvailableOrderServicesRet;
import main.com.bsuir.autoservice.dto.ServiceAvailableDTO;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.service.IServiceBeanService;
import main.com.bsuir.autoservice.service.impl.share.IShareService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonalAccountAvailableOrderServicesCommandTest {
    private IShareService shareService;
    private IServiceBeanService serviceBeanService;
    private PersonalAccountAvailableOrderServicesCommand personalAccountAvailableOrderServicesCommand;

    @Before
    public void beforeTest() {
        shareService = getShareService();
        serviceBeanService = getServiceBeanService();
        IServiceUnitOfWork mockUOF = getServiceUOF(shareService, serviceBeanService);
        IUserSession session = getSession();
        personalAccountAvailableOrderServicesCommand = getPersonalAccountAvailableOrderServicesCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(IShareService shareService, IServiceBeanService serviceBeanService){
        return new MockService.ServiceUOFBuilder()
                .setShareService(shareService)
                .setServiceBeanService(serviceBeanService)
                .build();
    }

    private static IShareService getShareService(){
        return MockService.getShareService();
    }

    private static IServiceBeanService getServiceBeanService(){
        return MockService.getServiceBeanService();
    }

    private static final int MOCK_USER_ID = MockBean.MOCK_USER_ID;

    private static PersonalAccountAvailableOrderServicesInfo getPersonalAccountAvailableOrderServicesInfo(){
        return mock(PersonalAccountAvailableOrderServicesInfo.class);
    }

    private static PersonalAccountAvailableOrderServicesRet getTestPersonalAccountAvailableOrderServicesRet(
            List<ServiceAvailableDTO> availableServices, List<Share> activeShares){
        return new PersonalAccountAvailableOrderServicesRet(availableServices, activeShares);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static PersonalAccountAvailableOrderServicesCommand getPersonalAccountAvailableOrderServicesCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new PersonalAccountAvailableOrderServicesCommand(serviceUnitOfWork, session);
    }

    private List<ServiceAvailableDTO> getMockAvailableServices() {
        return new ArrayList<ServiceAvailableDTO>(){{
            add(MockDTO.getMockServiceAvailableDTO());
        }};
    }
    private List<Share> getMockActiveShares() {
        return new ArrayList<Share>(){{
            add(MockBean.getMockShare());
        }};
    }

    @Test
    public void checkGetAvailableServices() throws CommandException, ServiceException {
        List<ServiceAvailableDTO> mockAvailableService = getMockAvailableServices();
        List<Share> mockActiveShares = getMockActiveShares();
        when(shareService.getActiveAccountShares(MOCK_USER_ID)).thenReturn(mockActiveShares);
        when(serviceBeanService.getAvailableServices()).thenReturn(mockAvailableService);
        assertEquals(personalAccountAvailableOrderServicesCommand.execute(getPersonalAccountAvailableOrderServicesInfo()),
                getTestPersonalAccountAvailableOrderServicesRet(mockAvailableService, mockActiveShares));
    }

    @Test(expected = CommandException.class)
    public void checkGetAvailableServicesException() throws ServiceException, CommandException {
        when(shareService.getActiveAccountShares(anyInt())).thenThrow(ServiceException.class);
        when(serviceBeanService.getAvailableServices()).thenThrow(ServiceException.class);
        personalAccountAvailableOrderServicesCommand.execute(getPersonalAccountAvailableOrderServicesInfo());
        fail();
    }
}
