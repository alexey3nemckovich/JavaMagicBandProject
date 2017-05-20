package unit.command.personalaccount;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.command.account.PersonalAccountMakeOrderCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountMakeOrderInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountMakeOrderRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.order.IOrderService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonalAccountMakeOrderTest {
    private IOrderService orderService;
    private PersonalAccountMakeOrderCommand personalAccountMakeOrderCommand;

    @Before
    public void beforeTest() {
        orderService = getOrderService();
        IServiceUnitOfWork mockUOF = getServiceUOF(orderService);
        IUserSession session = getSession();
        personalAccountMakeOrderCommand = getPersonalAccountMakeOrderCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(IOrderService service){
        return new MockService.ServiceUOFBuilder()
                .setOrderService(service)
                .build();
    }

    private static IOrderService getOrderService(){
        return MockService.getOrderService();
    }

    private static final int MOCK_USER_ID = MockBean.MOCK_USER_ID;

    private static PersonalAccountMakeOrderInfo getPersonalAccountMakeOrderInfo(){
        return mock(PersonalAccountMakeOrderInfo.class);
    }

    private static PersonalAccountMakeOrderRet getTestPersonalAccountMakeOrderRet(boolean isAddedOrder){
        return new PersonalAccountMakeOrderRet(isAddedOrder);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static PersonalAccountMakeOrderCommand getPersonalAccountMakeOrderCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new PersonalAccountMakeOrderCommand(serviceUnitOfWork, session);
    }

    private static List<Service> getMockMakeOrder(){
        return new ArrayList<Service>(){{
            add(MockBean.getMockService());
        }};
    }

    @Test
    public void checkAddedOrder() throws CommandException, ServiceException {
        List<Service> mockMakeOrder = getMockMakeOrder();
        PersonalAccountMakeOrderInfo mockInfo = getPersonalAccountMakeOrderInfo();
        when(mockInfo.getOrderServices()).thenReturn(mockMakeOrder);
        final boolean isAddedOrder = true;
        when(orderService.makeOrder(MOCK_USER_ID, mockMakeOrder)).thenReturn(isAddedOrder);
        assertEquals(personalAccountMakeOrderCommand.execute(mockInfo),
                getTestPersonalAccountMakeOrderRet(isAddedOrder));
    }

    @Test(expected = CommandException.class)
    public void checkAddedOrderException() throws ServiceException, CommandException {
        when(orderService.makeOrder(anyInt(), anyList())).thenThrow(ServiceException.class);
        personalAccountMakeOrderCommand.execute(getPersonalAccountMakeOrderInfo());
        fail();
    }
}
