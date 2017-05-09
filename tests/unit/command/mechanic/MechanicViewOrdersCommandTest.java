package unit.command.mechanic;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.mechanic.MechanicViewOrdersCommand;
import main.com.bsuir.autoservice.command.param.MechanicViewOrdersInfo;
import main.com.bsuir.autoservice.command.ret.MechanicViewOrdersRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.IOrderService;
import main.com.bsuir.autoservice.service.impl.IServiceService;
import main.com.bsuir.autoservice.service.unitOfWork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MechanicViewOrdersCommandTest {
    private IOrderService orderService;
    private MechanicViewOrdersCommand mechanicViewOrdersCommand;

    @Before
    public void beforeTest() {
        orderService = getOrderService();
        IServiceUnitOfWork mockUOF = getServiceUOF(orderService);
        IUserSession session = getSession();
        mechanicViewOrdersCommand = getMechanicViewOrdersCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(IOrderService orderService){
        return new MockService.ServiceUOFBuilder()
                .setOrderService(orderService)
                .build();
    }

    private static IOrderService getOrderService(){
        return MockService.getOrderService();
    }

    private static IServiceService getServiceService(){
        return MockService.getServiceService();
    }

    private static final int MOCK_STAFF_ID = MockBean.MOCK_USER_ID;

    private static MechanicViewOrdersInfo getMechanicViewOrdersInfo(){
        return mock(MechanicViewOrdersInfo.class);
    }

    private static MechanicViewOrdersRet getTestMechanicViewOrdersRet(List<order> serviceShopOrders){
        return new MechanicViewOrdersRet(serviceShopOrders);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static MechanicViewOrdersCommand getMechanicViewOrdersCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new MechanicViewOrdersCommand(serviceUnitOfWork, session);
    }

    private List<order> getMockServiceShopOrders() {
        return new ArrayList<order>() {{
            add(MockBean.getMockOrder());
        }};
    }

    @Test
    public void checkGetServiceShopOrders() throws CommandException, ServiceException {
        List<order> mockServiceShopOrders = getMockServiceShopOrders();
        when(orderService.getServiceShopOrders(eq(MOCK_STAFF_ID),
                any(MechanicViewOrdersInfo.SortedType.class), anyInt(), anyInt())).thenReturn(mockServiceShopOrders);
        assertEquals(mechanicViewOrdersCommand.execute(getMechanicViewOrdersInfo()),
                getTestMechanicViewOrdersRet(mockServiceShopOrders));
    }

    @Test(expected = CommandException.class)
    public void checkGetServiceShopOrdersException() throws ServiceException, CommandException {
        when(orderService.getServiceShopOrders(anyInt(), any(MechanicViewOrdersInfo.SortedType.class),
                anyInt(), anyInt())).thenThrow(ServiceException.class);
        mechanicViewOrdersCommand.execute(getMechanicViewOrdersInfo());
        fail();
    }
}
