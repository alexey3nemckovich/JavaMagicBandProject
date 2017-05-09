package unit.command.mechanic;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.mechanic.MechanicChangeOrderStateCommand;
import main.com.bsuir.autoservice.command.param.MechanicChangeOrderStateInfo;
import main.com.bsuir.autoservice.command.ret.MechanicChangeOrderStateRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.crud.IOrderService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MechanicChangeOrderStateCommandTest {
    private IOrderService orderService;
    private MechanicChangeOrderStateCommand mechanicChangeOrderStateCommand;

    @Before
    public void beforeTest() {
        orderService = getOrderService();
        IServiceUnitOfWork mockUOF = getServiceUOF(orderService);
        IUserSession session = getSession();
        mechanicChangeOrderStateCommand = getMechanicChangeOrderStateCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(IOrderService orderService){
        return new MockService.ServiceUOFBuilder()
                .setOrderService(orderService)
                .build();
    }

    private static IOrderService getOrderService(){
        return MockService.getOrderService();
    }

    private static MechanicChangeOrderStateInfo getMechanicChangeOrderStateInfo(){
        return mock(MechanicChangeOrderStateInfo.class);
    }

    private static MechanicChangeOrderStateRet getTestMechanicChangeOrderStateRet(boolean isChangedOrderState){
        return new MechanicChangeOrderStateRet(isChangedOrderState);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static MechanicChangeOrderStateCommand getMechanicChangeOrderStateCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new MechanicChangeOrderStateCommand(serviceUnitOfWork, session);
    }


    private static final int MOCK_USER_STAFF_ID = MockBean.MOCK_USER_ID;
    private static final int MOCK_ORDER_ID = MockBean.MOCK_ORDER_ID;

    @Test
    public void checkChangedOrderState() throws CommandException, ServiceException {
        final boolean verifyIsChangedOrderState = true;
        when(orderService.changeOrderState(eq(MOCK_USER_STAFF_ID), eq(MOCK_ORDER_ID), any(order.State.class))).
                thenReturn(verifyIsChangedOrderState);
        MechanicChangeOrderStateInfo mockInfo = getMechanicChangeOrderStateInfo();
        when(mockInfo.getOrderId()).thenReturn(MOCK_ORDER_ID);
        assertEquals(mechanicChangeOrderStateCommand.execute(mockInfo),
                getTestMechanicChangeOrderStateRet(verifyIsChangedOrderState));
    }

    @Test(expected = CommandException.class)
    public void checkChangedOrderStateException() throws ServiceException, CommandException {
        when(orderService.changeOrderState(anyInt(), anyInt(), any(order.State.class))).thenThrow(ServiceException.class);
        mechanicChangeOrderStateCommand.execute(getMechanicChangeOrderStateInfo());
        fail();
    }
}
