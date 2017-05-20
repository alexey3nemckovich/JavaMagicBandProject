package unit.command.main;

import general.bean.MockBean;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.bean.impl.Share;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.main.GeneralInformationCommand;
import main.com.bsuir.autoservice.command.param.GeneralInformationInfo;
import main.com.bsuir.autoservice.command.ret.GeneralInformationRet;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneralInformationCommandTest {
    private GeneralInformationCommand generalInformationCommand;
    private IServiceBeanService serviceBeanService;
    private IShareService shareService;

    @Before
    public void beforeTest() {
        serviceBeanService = getServiceBeanService();
        shareService = getShareService();
        IServiceUnitOfWork mockUOF = getServiceUOF(serviceBeanService, shareService);
        generalInformationCommand = getGeneralInformationCommand(mockUOF);
    }

    private static GeneralInformationCommand getGeneralInformationCommand(IServiceUnitOfWork serviceUnitOfWork) {
        return new GeneralInformationCommand(serviceUnitOfWork);
    }

    private static IServiceUnitOfWork getServiceUOF(IServiceBeanService serviceBeanService, IShareService shareService){
        return new MockService.ServiceUOFBuilder()
                .setServiceBeanService(serviceBeanService)
                .setShareService(shareService)
                .build();
    }

    private static IServiceBeanService getServiceBeanService(){
        return MockService.getServiceBeanService();
    }

    private static IShareService getShareService() {
        return MockService.getShareService();
    }

    private static GeneralInformationInfo getGeneralInformationInfo(){
        return mock(GeneralInformationInfo.class);
    }

    private static List<Service> getMockAvailableServices(){
        return new ArrayList<Service>(){{
               add(MockBean.getMockService());
            }};
    }

    private static List<Share> getMockActiveShares(){
        return new ArrayList<Share>(){{
            add(MockBean.getMockShare());
        }};
    }

    private static GeneralInformationRet getTestGeneralInformationRet(List<Service> availableServices,
                                                                      List<Share> activeShares){
        return new GeneralInformationRet(availableServices, activeShares);
    }

    @Test
    public void getGeneralInformationAll() throws CommandException, ServiceException {
        List<Service> mockAvailableServices = getMockAvailableServices();
        List<Share> mockActiveShares = getMockActiveShares();
        when(serviceBeanService.getAvailableServices()).thenReturn(mockAvailableServices);
        when(shareService.getActiveShares()).thenReturn(mockActiveShares);
        assertEquals(generalInformationCommand.execute(getGeneralInformationInfo()),
                getTestGeneralInformationRet(mockAvailableServices, mockActiveShares));
    }

    @Test(expected = CommandException.class)
    public void getGeneralInformationException() throws ServiceException, CommandException {
        when(serviceBeanService.getAvailableServices()).thenThrow(ServiceException.class);
        when(shareService.getActiveShares()).thenThrow(ServiceException.class);
        generalInformationCommand.execute(getGeneralInformationInfo());
        fail();
    }
}
