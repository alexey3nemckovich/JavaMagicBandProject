package unit.command.mechanic;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.spare_part;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.mechanic.ChiefMechanicViewSparePartCommand;
import main.com.bsuir.autoservice.command.param.ChiefMechanicViewSparePartInfo;
import main.com.bsuir.autoservice.command.ret.ChiefMechanicViewSparePartRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.ISparePartService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChiefMechanicViewSparePartCommandTest {
    private ISparePartService sparePartService;
    private ChiefMechanicViewSparePartCommand chiefMechanicViewSparePartCommand;

    @Before
    public void beforeTest() {
        sparePartService = getSparePartService();
        IServiceUnitOfWork mockUOF = getServiceUOF(sparePartService);
        IUserSession session = getSession();
        chiefMechanicViewSparePartCommand = getChiefMechanicViewSparePartCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(ISparePartService sparePartService) {
        return new MockService.ServiceUOFBuilder()
                .setSparePartService(sparePartService)
                .build();
    }

    private static ISparePartService getSparePartService() {
        return MockService.getSparePartService();
    }

    private static ChiefMechanicViewSparePartInfo getChiefMechanicViewSparePartInfo() {
        return mock(ChiefMechanicViewSparePartInfo.class);
    }

    private static ChiefMechanicViewSparePartRet getTestChiefMechanicViewSparePartRet(List<spare_part> groupSparePart) {
        return new ChiefMechanicViewSparePartRet(groupSparePart);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static ChiefMechanicViewSparePartCommand getChiefMechanicViewSparePartCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session) {
        return new ChiefMechanicViewSparePartCommand(serviceUnitOfWork, session);
    }

    private List<spare_part> getMockSpareParts() {
        return new ArrayList<spare_part>() {{
            add(MockBean.getMockSparePart());
        }};
    }


    private static final int MOCK_USER_STAFF_ID = MockBean.MOCK_USER_ID;

    @Test
    public void getViewSharePart() throws CommandException, ServiceException {
        List<spare_part> mockSparePart = getMockSpareParts();
        when(sparePartService.getView(eq(MOCK_USER_STAFF_ID), anyInt(), anyInt())).thenReturn(mockSparePart);
        assertEquals(chiefMechanicViewSparePartCommand.execute(getChiefMechanicViewSparePartInfo()),
                getTestChiefMechanicViewSparePartRet(mockSparePart));
    }

    @Test(expected = CommandException.class)
    public void getViewSharePartException() throws ServiceException, CommandException {
        when(sparePartService.getView(anyInt(), anyInt(), anyInt())).thenThrow(ServiceException.class);
        chiefMechanicViewSparePartCommand.execute(getChiefMechanicViewSparePartInfo());
        fail();
    }
}
