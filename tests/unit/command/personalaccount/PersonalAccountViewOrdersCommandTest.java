package unit.command.personalaccount;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.command.account.PersonalAccountViewOrdersCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountViewOrdersInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountViewOrdersRet;
import main.com.bsuir.autoservice.infrastructure.session.IUserSession;
import main.com.bsuir.autoservice.service.crud.IOrderService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;
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

public class PersonalAccountViewOrdersCommandTest {    
    private IOrderService orderService;
    private PersonalAccountViewOrdersCommand personalAccountViewOrdersCommand;

    @Before
    public void beforeTest() {
        orderService = getOrderService();
        IServiceUnitOfWork mockUOF = getServiceUOF(orderService);
        IUserSession session = getSession();
        personalAccountViewOrdersCommand = getPersonalAccountViewOrdersCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(IOrderService service){
        return new MockService.ServiceUOFBuilder()
                .setOrderService(service)
                .build();
    }

    private static IOrderService getOrderService(){
        return MockService.getOrderService();
    }

    private static final int MOCK_USER_ID = MockSession.MOCK_SESSION_ID;

    private static PersonalAccountViewOrdersInfo getPersonalAccountViewOrdersInfo(){
        return mock(PersonalAccountViewOrdersInfo.class);
    }

    private static PersonalAccountViewOrdersRet getTestPersonalAccountViewOrdersRet(List<order> orders){
        return new PersonalAccountViewOrdersRet(orders);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static PersonalAccountViewOrdersCommand getPersonalAccountViewOrdersCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new PersonalAccountViewOrdersCommand(serviceUnitOfWork, session);
    }

    private static List<order> getMockViewOrders(){
        return new ArrayList<order>(){{
            add(MockBean.getMockOrder());
        }};
    }

    @Test
    public void checkGetViewOrders() throws CommandException, ServiceException {
        List<order> mockViewOrders = getMockViewOrders();
        when(orderService.getUserOrders(eq(MOCK_USER_ID), anyInt(), anyInt())).thenReturn(mockViewOrders);
        assertEquals(personalAccountViewOrdersCommand.execute(getPersonalAccountViewOrdersInfo()),
                getTestPersonalAccountViewOrdersRet(mockViewOrders));
    }

    @Test(expected = CommandException.class)
    public void checkGetViewOrdersException() throws ServiceException, CommandException {
        when(orderService.getUserOrders(anyInt(), anyInt(), anyInt())).thenThrow(ServiceException.class);
        personalAccountViewOrdersCommand.execute(getPersonalAccountViewOrdersInfo());
        fail();
    }
}
