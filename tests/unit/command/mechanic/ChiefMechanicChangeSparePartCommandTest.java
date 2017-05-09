package unit.command.mechanic;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.spare_part;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.mechanic.ChiefMechanicChangeSparePartCommand;
import main.com.bsuir.autoservice.command.param.ChiefMechanicChangeSparePartInfo;
import main.com.bsuir.autoservice.command.ret.ChiefMechanicChangeSparePartRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.ISparePartService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChiefMechanicChangeSparePartCommandTest {
    private ISparePartService sparePartService;
    private ChiefMechanicChangeSparePartCommand chiefMechanicChangeSparePartCommand;

    @Before
    public void beforeTest() {
        sparePartService = getSparePartService();
        IServiceUnitOfWork mockUOF = getServiceUOF(sparePartService);
        IUserSession session = getSession();
        chiefMechanicChangeSparePartCommand = getChiefMechanicChangeSparePartCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(ISparePartService sparePartService) {
        return new MockService.ServiceUOFBuilder()
                .setSparePartService(sparePartService)
                .build();
    }

    private static ISparePartService getSparePartService() {
        return MockService.getSparePartService();
    }

    private static ChiefMechanicChangeSparePartInfo getChiefMechanicChangeSparePartInfo() {
        return mock(ChiefMechanicChangeSparePartInfo.class);
    }

    private static ChiefMechanicChangeSparePartRet getTestChiefMechanicChangeSparePartRet(boolean isUpdateSparePart) {
        return new ChiefMechanicChangeSparePartRet(isUpdateSparePart);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static ChiefMechanicChangeSparePartCommand getChiefMechanicChangeSparePartCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        return new ChiefMechanicChangeSparePartCommand(serviceUnitOfWork, session);
    }

    private static spare_part getMockSparePart(){
        return MockBean.getMockSparePart();
    }

    @Test
    public void getViewSharePart() throws CommandException, ServiceException {
        final boolean verifyUpdateSparePart = true;
        spare_part mockSparePart = getMockSparePart();
        when(sparePartService.updateSparePart(mockSparePart)).thenReturn(verifyUpdateSparePart);
        ChiefMechanicChangeSparePartInfo mockInfo = getChiefMechanicChangeSparePartInfo();
        when(mockInfo.getSparePart()).thenReturn(mockSparePart);
        assertEquals(chiefMechanicChangeSparePartCommand.execute(mockInfo),
                getTestChiefMechanicChangeSparePartRet(verifyUpdateSparePart));
    }

    @Test(expected = CommandException.class)
    public void getViewSharePartException() throws ServiceException, CommandException {
        when(sparePartService.updateSparePart(any(spare_part.class))).thenThrow(ServiceException.class);
        chiefMechanicChangeSparePartCommand.execute(getChiefMechanicChangeSparePartInfo());
        fail();
    }
}
