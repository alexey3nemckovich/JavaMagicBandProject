package unit.command.main;

import general.bean.MockBean;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.bean.share;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.main.GeneralInformationCommand;
import main.com.bsuir.autoservice.command.param.GeneralInformationInfo;
import main.com.bsuir.autoservice.command.ret.GeneralInformationRet;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneralInformationCommandTest {
    private GeneralInformationCommand generalInformationCommand;
    private IServiceService serviceService;
    private IShareService shareService;

    @Before
    public void beforeTest() {
        serviceService = getServiceService();
        shareService = getShareService();
        IServiceUnitOfWork mockUOF = getServiceUOF(serviceService, shareService);
        generalInformationCommand = getGeneralInformationCommand(mockUOF);
    }

    private static GeneralInformationCommand getGeneralInformationCommand(IServiceUnitOfWork serviceUnitOfWork) {
        return new GeneralInformationCommand(serviceUnitOfWork);
    }

    private static IServiceUnitOfWork getServiceUOF(IServiceService serviceService, IShareService shareService){
        return new MockService.ServiceUOFBuilder()
                .setServiceService(serviceService)
                .setShareService(shareService)
                .build();
    }

    private static IServiceService getServiceService(){
        return MockService.getServiceService();
    }

    private static IShareService getShareService() {
        return MockService.getShareService();
    }

    private static GeneralInformationInfo getGeneralInformationInfo(){
        return mock(GeneralInformationInfo.class);
    }

    private static List<service> getMockAvailableServices(){
        return new ArrayList<service>(){{
               add(MockBean.getMockService());
            }};
    }

    private static List<share> getMockActiveShares(){
        return new ArrayList<share>(){{
            add(MockBean.getMockShare());
        }};
    }

    private static GeneralInformationRet getTestGeneralInformationRet(List<service> availableServices,
                                                                      List<share> activeShares){
        return new GeneralInformationRet(availableServices, activeShares);
    }

    @Test
    public void getGeneralInformationAll() throws CommandException, ServiceException {
        List<service> mockAvailableServices = getMockAvailableServices();
        List<share> mockActiveShares = getMockActiveShares();
        when(serviceService.getAvailableServices()).thenReturn(mockAvailableServices);
        when(shareService.getActiveShares()).thenReturn(mockActiveShares);
        assertEquals(generalInformationCommand.execute(getGeneralInformationInfo()),
                getTestGeneralInformationRet(mockAvailableServices, mockActiveShares));
    }

    @Test(expected = CommandException.class)
    public void getGeneralInformationException() throws ServiceException, CommandException {
        when(serviceService.getAvailableServices()).thenThrow(ServiceException.class);
        when(shareService.getActiveShares()).thenThrow(ServiceException.class);
        generalInformationCommand.execute(getGeneralInformationInfo());
        fail();
    }
}
