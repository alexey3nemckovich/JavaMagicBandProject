package unit.command.crud.edit;

import general.bean.MockBean;
import general.dao.database.map.MockDatabaseMap;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.command.crud.edit.EditBeanCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanEditPageInfo;
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

public class EditBeanCommandTest {
    private ICrudService crudService;
    private IDatabaseMap databaseMap;
    private EditBeanCommand editBeanCommand;

    @Before
    public void beforeTest(){
        crudService = getCrudService();
        databaseMap = getDatabaseMap();
        IServiceUnitOfWork serviceUOF = getServiceUOF(crudService);
        editBeanCommand = getEditBeanCommand(serviceUOF, databaseMap);
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

    private static EditBeanCommand getEditBeanCommand(IServiceUnitOfWork serviceUnitOfWork, IDatabaseMap databaseMap){
        return new EditBeanCommand(serviceUnitOfWork, databaseMap);
    }

    private static BeanEditPageInfo getBeanEditPageInfo(){
        return mock(BeanEditPageInfo.class);
    }

    @Test
    public void checkEditedBean() throws CommandException, ServiceException, BeanException {
        Bean bean = MockBean.getMockUser();
        when(databaseMap.getBeanInstance(anyString(), anyMap())).thenReturn(bean);
        final boolean isEdited = true;
        when(crudService.update(anyString(), Matchers.eq(bean), anyMap())).thenReturn(isEdited);
        BeanEditPageInfo expectedBeanEditPageInfo = getBeanEditPageInfo();
        expectedBeanEditPageInfo.result = "Operation success";
        assertEquals(editBeanCommand.execute(getBeanEditPageInfo()).result, expectedBeanEditPageInfo.result);
    }

    @Test(expected = CommandException.class)
    public void checkEditedBeanException() throws CommandException, ServiceException, BeanException{
        Bean bean = MockBean.getMockUser();
        when(databaseMap.getBeanInstance(anyString(), anyMap())).thenReturn(bean);
        when(crudService.update(anyString(), Matchers.eq(bean), anyMap())).thenThrow(Exception.class);
        editBeanCommand.execute(getBeanEditPageInfo());
        fail();
    }
}
