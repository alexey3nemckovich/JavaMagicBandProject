package unit.command.crud.delete;

import general.bean.MockBean;
import general.dao.database.map.MockDatabaseMap;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.command.crud.delete.DeleteBeanDependencyCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteBeanDependencyCommandTest {
    private ICrudService crudService;
    private IDatabaseMap databaseMap;
    private DeleteBeanDependencyCommand deleteBeanDependencyCommand;

    @Before
    public void beforeTest() {
        crudService = getCrudService();
        databaseMap = getDatabaseMap();
        IServiceUnitOfWork serviceUOF = getServiceUOF(crudService);
        deleteBeanDependencyCommand = getDeleteBeanDependencyCommand(serviceUOF, databaseMap);
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

    private static DeleteBeanDependencyCommand getDeleteBeanDependencyCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        return new DeleteBeanDependencyCommand(serviceUnitOfWork, databaseMap);
    }

    private static BeanDependencyViewPageInfo getBeanDependencyViewPageInfo(){
        BeanDependencyViewPageInfo beanDependencyViewPageInfo = mock(BeanDependencyViewPageInfo.class);
        beanDependencyViewPageInfo.dependency = mock(Dependency.class);
        beanDependencyViewPageInfo.countRecords = 1;
        return beanDependencyViewPageInfo;
    }

    @Test
    public void checkDeletedBeanDependency() throws CommandException, ServiceException, BeanException {
        Bean bean = MockBean.getMockUser();
        when(databaseMap.getBeanInstance(anyString(), anyMap())).thenReturn(bean);
        final boolean isDeleted = true;
        when(crudService.delete(anyString(), Matchers.eq(bean))).thenReturn(isDeleted);
        when(crudService.readTotalCount(anyString())).thenReturn(1);
        BeanDependencyViewPageInfo expectedBeanDependencyViewPageInfo = getBeanDependencyViewPageInfo();
        expectedBeanDependencyViewPageInfo.result = "Operation success";
        assertEquals(deleteBeanDependencyCommand.execute(getBeanDependencyViewPageInfo()).result, expectedBeanDependencyViewPageInfo.result);
    }

    @Test(expected = CommandException.class)
    public void checkDeletedBeanDependencyException() throws CommandException, ServiceException, BeanException {
        Bean bean = MockBean.getMockUser();
        when(databaseMap.getBeanInstance(anyString(), anyMap())).thenReturn(bean);
        when(crudService.delete(anyString(), Matchers.eq(bean))).thenThrow(Exception.class);
        deleteBeanDependencyCommand.execute(getBeanDependencyViewPageInfo());
        fail();
    }
}
