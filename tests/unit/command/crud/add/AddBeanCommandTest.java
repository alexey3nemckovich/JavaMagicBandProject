package unit.command.crud.add;

import general.bean.MockBean;
import general.dao.database.map.MockDatabaseMap;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.command.crud.add.AddBeanCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanAddPageInfo;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
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

public class AddBeanCommandTest{
    private ICrudService crudService;
    private IDatabaseMap databaseMap;
    private AddBeanCommand addBeanCommand;

    @Before
    public void beforeTest(){
        crudService = getCrudService();
        IServiceUnitOfWork serviceUOF = getServiceUOF(crudService);
        databaseMap = getDatabaseMap();
        addBeanCommand = getAddBeanCommand(serviceUOF, databaseMap);
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

    private static BeanAddPageInfo getBeanAddPageInfo(){
        return mock(BeanAddPageInfo.class);
    }

    private static AddBeanCommand getAddBeanCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        return new AddBeanCommand(serviceUnitOfWork, databaseMap);
    }

    @Test
    public void checkAddedBean() throws CommandException, ServiceException, BeanException {
        Bean bean = MockBean.getMockUser();
        when(databaseMap.getBeanInstance(anyString(), anyMap())).thenReturn(bean);
        final boolean isCreated = true;
        when(crudService.create(anyString(), Matchers.eq(bean))).thenReturn(isCreated);
        BeanAddPageInfo expectedBeanAddPageInfo = getBeanAddPageInfo();
        expectedBeanAddPageInfo.result = "Operation success";
        assertEquals(addBeanCommand.execute(getBeanAddPageInfo()).result, expectedBeanAddPageInfo.result);
    }

    @Test(expected = CommandException.class)
    public void checkAddedBeanException() throws CommandException, ServiceException, BeanException {
        Bean bean = MockBean.getMockUser();
        when(databaseMap.getBeanInstance(anyString(), anyMap())).thenReturn(bean);
        when(crudService.create(anyString(), Matchers.eq(bean))).thenThrow(Exception.class);
        addBeanCommand.execute(getBeanAddPageInfo());
        fail();
    }

}
