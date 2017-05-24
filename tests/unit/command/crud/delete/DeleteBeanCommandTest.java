package unit.command.crud.delete;

import general.bean.MockBean;
import general.dao.database.map.MockDatabaseMap;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.command.crud.delete.DeleteBeanCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.Matchers;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;

public class DeleteBeanCommandTest {
    private ICrudService crudService;
    private IDatabaseMap databaseMap;
    private DeleteBeanCommand deleteBeanCommand;

    @Before
    public void beforeTest() {
        crudService = getCrudService();
        databaseMap = getDatabaseMap();
        IServiceUnitOfWork serviceUOF = getServiceUOF(crudService);
        deleteBeanCommand = getDeleteBeanCommand(serviceUOF, databaseMap);
    }

    private static IServiceUnitOfWork getServiceUOF(ICrudService service){
        return new MockService.ServiceUOFBuilder()
                .setCrudService(service)
                .build();
    }

    private static ICrudService getCrudService(){
        return MockService.getCrudService();
    }

    private static IDatabaseMap getDatabaseMap(){
        return MockDatabaseMap.getMockDatabaseMap();
    }

    private static DeleteBeanCommand getDeleteBeanCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        return new DeleteBeanCommand(serviceUnitOfWork, databaseMap);
    }

    private static BeanViewPageInfo getBeanViewPageInfo(){
        BeanViewPageInfo beanViewPageInfo = mock(BeanViewPageInfo.class);
        beanViewPageInfo.countRecords = 1;
        return beanViewPageInfo;
    }

    @Test
    public void checkDeletedBean() throws CommandException, ServiceException, BeanException {
        Bean bean = MockBean.getMockUser();
        when(databaseMap.getBeanInstance(anyString(), anyMap())).thenReturn(bean);
        final boolean isDeleted = true;
        when(crudService.delete(anyString(), Matchers.eq(bean))).thenReturn(isDeleted);
        when(crudService.readTotalCount(anyString())).thenReturn(1);
        BeanViewPageInfo expectedBeanViewPageInfo = getBeanViewPageInfo();
        expectedBeanViewPageInfo.result = "Operation success";
        assertEquals(deleteBeanCommand.execute(getBeanViewPageInfo()).result, expectedBeanViewPageInfo.result);
    }

    @Test(expected = CommandException.class)
    public void checkDeletedBeanException() throws CommandException, ServiceException, BeanException {
        Bean bean = MockBean.getMockUser();
        when(databaseMap.getBeanInstance(anyString(), anyMap())).thenReturn(bean);
        when(crudService.delete(anyString(), Matchers.eq(bean))).thenThrow(Exception.class);
        deleteBeanCommand.execute(getBeanViewPageInfo());
        fail();
    }
}
