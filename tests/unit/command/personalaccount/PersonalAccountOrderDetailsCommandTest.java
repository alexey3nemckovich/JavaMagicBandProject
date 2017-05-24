package unit.command.personalaccount;

import general.bean.MockBean;
import general.service.MockService;
import general.session.MockSession;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.command.account.PersonalAccountOrderDetailsCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.PersonalAccountOrderDetailsInfo;
import main.com.bsuir.autoservice.command.ret.PersonalAccountOrderDetailsRet;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonalAccountOrderDetailsCommandTest {
    private IOrderService orderService;
    private PersonalAccountOrderDetailsCommand personalAccountOrderDetailsCommand;

    @Before
    public void beforeTest() {
        orderService = getOrderService();
        IServiceUnitOfWork mockUOF = getServiceUOF(orderService);
        IUserSession session = getSession();
        personalAccountOrderDetailsCommand = getPersonalAccountOrderDetailsCommand(mockUOF, session);
    }

    private static IServiceUnitOfWork getServiceUOF(IOrderService service){
        return new MockService.ServiceUOFBuilder()
                .setOrderService(service)
                .build();
    }

    private static IOrderService getOrderService(){
        return MockService.getOrderService();
    }

    private static PersonalAccountOrderDetailsInfo getPersonalAccountOrderDetailsInfo(){
        return mock(PersonalAccountOrderDetailsInfo.class);
    }

    private static PersonalAccountOrderDetailsRet getTestPersonalAccountOrderDetailsRet(List<Service> services){
        return new PersonalAccountOrderDetailsRet(services);
    }

    private static IUserSession getSession() {
        return MockSession.getSession();
    }

    private static PersonalAccountOrderDetailsCommand getPersonalAccountOrderDetailsCommand(
            IServiceUnitOfWork serviceUnitOfWork, IUserSession session){
        return new PersonalAccountOrderDetailsCommand(serviceUnitOfWork, session);
    }

    private static List<Service> getMockOrderServices(){
        return new ArrayList<Service>(){{
            add(MockBean.getMockService());
        }};
    }

    private static final int MOCK_ORDER_ID = MockBean.MOCK_ORDER_ID;
    private static final int MOCK_USER_ID = MockBean.MOCK_USER_ID;
    
    @Test
    public void checkGetOrderServicesDetail() throws CommandException, ServiceException {
        List<Service> mockOrderServices = getMockOrderServices();
        when(orderService.getOrderServices(MOCK_USER_ID, MOCK_ORDER_ID)).thenReturn(mockOrderServices);
        PersonalAccountOrderDetailsInfo mockInfo = getPersonalAccountOrderDetailsInfo();
        when(mockInfo.getDetailOrderId()).thenReturn(MOCK_ORDER_ID);
        assertEquals(personalAccountOrderDetailsCommand.execute(mockInfo),
                getTestPersonalAccountOrderDetailsRet(mockOrderServices));
    }

    @Test(expected = CommandException.class)
    public void checkGetOrderServicesDetailException() throws ServiceException, CommandException {
        when(orderService.getOrderServices(anyInt(), anyInt())).thenThrow(ServiceException.class);
        personalAccountOrderDetailsCommand.execute(getPersonalAccountOrderDetailsInfo());
        fail();
    }
}
