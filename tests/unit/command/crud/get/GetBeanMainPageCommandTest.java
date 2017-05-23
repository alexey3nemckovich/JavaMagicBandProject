package unit.command.crud.get;

import general.service.MockService;
import main.com.bsuir.autoservice.command.crud.get.GetBeanMainPageCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanMainPageInfo;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.baseservice.IBaseService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ComponentAdapter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetBeanMainPageCommandTest {
    IServiceUnitOfWork serviceUnitOfWork;
    GetBeanMainPageCommand getBeanMainPageCommand;

    @Before
    public void beforeTest() {
        serviceUnitOfWork = getServiceUOF();
        getBeanMainPageCommand = getGetBeanMainPageCommand(serviceUnitOfWork);
    }

    private static IServiceUnitOfWork getServiceUOF(){
        return new MockService.ServiceUOFBuilder()
                .build();
    }

    private static GetBeanMainPageCommand getGetBeanMainPageCommand(IServiceUnitOfWork serviceUnitOfWork) {
        return  new GetBeanMainPageCommand(serviceUnitOfWork);
    }

    private static BeanMainPageInfo getBeanMainPageInfo() {
        return mock(BeanMainPageInfo.class);
    }

    @Test
    public void checkGetBeanMainPage() throws CommandException, ServiceException {
        IBaseService baseService = mock(IBaseService.class);
        when(serviceUnitOfWork.getBaseService()).thenReturn(baseService);
        List<String> listTableNames = mock(List.class);
        when(baseService.getListTableNames()).thenReturn(listTableNames);
        assertEquals(getBeanMainPageCommand.execute(getBeanMainPageInfo()).dbBeanNames, listTableNames);
    }

    @Test(expected = CommandException.class)
    public void checkGetBeanMainPageException() throws CommandException {
        when(serviceUnitOfWork.getBaseService()).thenThrow(Exception.class);
        getBeanMainPageCommand.execute(getBeanMainPageInfo());
        fail();
    }
}
