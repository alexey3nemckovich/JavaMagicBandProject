package unit.command.crud.get;

import general.bean.MockBean;
import general.dao.database.map.MockDatabaseMap;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.command.crud.get.GetBeanAddPageCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanAddPageInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;

import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.StringJoiner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetBeanAddPageCommandTest {
    private IDatabaseMap databaseMap;
    private GetBeanAddPageCommand getBeanAddPageCommand;

    @Before
    public void beforeTest() {
        databaseMap = getDatabaseMap();
        IServiceUnitOfWork serviceUOF = getServiceUOF();
        getBeanAddPageCommand = getGetBeanAddPageCommand(serviceUOF, databaseMap);
    }

    private static IServiceUnitOfWork getServiceUOF(){
        return new MockService.ServiceUOFBuilder()
                .build();
    }

    private static IDatabaseMap getDatabaseMap(){
        return MockDatabaseMap.getMockDatabaseMap();
    }

    private static GetBeanAddPageCommand getGetBeanAddPageCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap) {
        return new GetBeanAddPageCommand(serviceUnitOfWork, databaseMap);
    }

    private static BeanAddPageInfo getBeanAddPageInfo() {
        BeanAddPageInfo beanAddPageInfo = mock(BeanAddPageInfo.class);
        beanAddPageInfo.defaultValues = mock(Map.class);
        return beanAddPageInfo;
    }

    @Test
    public void checkGetBeanAddPage() throws CommandException, BeanException {
        Bean bean = MockBean.getMockUser();
        when(databaseMap.getBeanInstance(anyString())).thenReturn(bean);
        BeanAddPageInfo expectedBeanAddPageInfo = getBeanAddPageInfo();
        expectedBeanAddPageInfo.action = "add";
        assertEquals(getBeanAddPageCommand.execute(getBeanAddPageInfo()).action, expectedBeanAddPageInfo.action);
    }

    @Test(expected = CommandException.class)
    public void checkGetBeanAddPageException() throws CommandException, BeanException {
        when(databaseMap.getBeanInstance(anyString())).thenThrow(Exception.class);
        getBeanAddPageCommand.execute(getBeanAddPageInfo());
        fail();
    }

}
