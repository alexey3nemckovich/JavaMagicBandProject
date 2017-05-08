package unit.command.personalaccount;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.bean.share;
import main.com.bsuir.autoservice.command.account.PersonalAccountAvailableOrderServicesCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountAvailableOrderServicesInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountAvailableOrderServicesRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.IServiceService;
import main.com.bsuir.autoservice.service.IShareService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
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

public class PersonalAccountAvailableOrderServicesCommandTest {
    private IShareService shareService;
    private IServiceService serviceService;
    private PersonalAccountAvailableOrderServicesCommand personalAccountAvailableOrderServicesCommand;

    @Before
    public void beforeTest() {
        shareService = getShareService();
        serviceService = getServiceService();
        IServiceUnitOfWork mockUOF = getServiceUOF(shareService, serviceService);
        IUserSession session = getSession();
        personalAccountAvailableOrderServicesCommand = getPersonalAccountAvailableOrderServicesCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(IShareService shareService, IServiceService serviceService){
        return new MockService.ServiceUOFBuilder()
                .setShareService(shareService)
                .setServiceService(serviceService)
                .build();
    }

    private static IShareService getShareService(){
        return MockService.getShareService();
    }

    private static IServiceService getServiceService(){
        return MockService.getServiceService();
    }

    private static final int MOCK_USER_ID = MockBean.MOCK_USER_ID;

    private static PersonalAccountAvailableOrderServicesInfo getPersonalAccountAvailableOrderServicesInfo(){
        return mock(PersonalAccountAvailableOrderServicesInfo.class);
    }

    private static PersonalAccountAvailableOrderServicesRet getTestPersonalAccountAvailableOrderServicesRet(
            List<service> availableServices, List<share> activeShares){
        return new PersonalAccountAvailableOrderServicesRet(availableServices, activeShares);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static PersonalAccountAvailableOrderServicesCommand getPersonalAccountAvailableOrderServicesCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new PersonalAccountAvailableOrderServicesCommand(serviceUnitOfWork, session);
    }

    private List<service> getMockAvailableServices() {
        return new ArrayList<service>(){{
            add(MockBean.getMockService());
        }};
    }
    private List<share> getMockActiveShares() {
        return new ArrayList<share>(){{
            add(MockBean.getMockShare());
        }};
    }

    @Test
    public void checkGetAvailableServices() throws CommandException, ServiceException {
        List<service> mockAvailableService = getMockAvailableServices();
        List<share> mockActiveShares = getMockActiveShares();
        when(shareService.getActiveAccountShares(MOCK_USER_ID)).thenReturn(mockActiveShares);
        when(serviceService.getAvailableServices()).thenReturn(mockAvailableService);
        assertEquals(personalAccountAvailableOrderServicesCommand.execute(getPersonalAccountAvailableOrderServicesInfo()),
                getTestPersonalAccountAvailableOrderServicesRet(mockAvailableService, mockActiveShares));
    }

    @Test(expected = CommandException.class)
    public void checkGetAvailableServicesException() throws ServiceException, CommandException {
        when(shareService.getActiveAccountShares(anyInt())).thenThrow(ServiceException.class);
        when(serviceService.getAvailableServices()).thenThrow(ServiceException.class);
        personalAccountAvailableOrderServicesCommand.execute(getPersonalAccountAvailableOrderServicesInfo());
        fail();
    }
}
