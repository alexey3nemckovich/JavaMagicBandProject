package unit.command.crud.get;

import general.service.MockService;
import main.com.bsuir.autoservice.command.crud.get.GetBeanEditPageCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanEditPageInfo;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetBeanEditPageCommandTest {
    GetBeanEditPageCommand getBeanEditPageCommand;

    @Before
    public void beforeTest() {
        IServiceUnitOfWork serviceUOF = getServiceUOF();
        getBeanEditPageCommand = getGetBeanAddPageCommand(serviceUOF);
    }

    private static IServiceUnitOfWork getServiceUOF(){
        return new MockService.ServiceUOFBuilder()
                .build();
    }

    private static GetBeanEditPageCommand getGetBeanAddPageCommand(IServiceUnitOfWork serviceUnitOfWork) {
        return  new GetBeanEditPageCommand(serviceUnitOfWork);
    }

    private static BeanEditPageInfo getBeanEditPageInfo() {
        return mock(BeanEditPageInfo.class);
    }

    @Test
    public void checkGetBeanEditPage() throws CommandException {
        BeanEditPageInfo expectedBeanEditPageInfo = getBeanEditPageInfo();
        expectedBeanEditPageInfo.action = "edit";
        assertEquals(getBeanEditPageCommand.execute(getBeanEditPageInfo()).action, expectedBeanEditPageInfo.action);
    }
}
