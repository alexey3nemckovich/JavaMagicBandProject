package unit.command.crud.get;

import general.bean.DefaultBean;
import general.bean.MockBean;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.command.crud.get.GetBeanViewPageCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanViewPageInfo;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetBeanViewPageCommandTest {
    private ICrudService crudService;
    private GetBeanViewPageCommand getBeanViewPageCommand;

    @Before
    public void beforeTest() {
        crudService = getCrudService();
        IServiceUnitOfWork serviceUOF = getServiceUOF(crudService);
        getBeanViewPageCommand = getGetBeanViewPageCommand(serviceUOF);
    }

    private static IServiceUnitOfWork getServiceUOF(ICrudService service){
        return new MockService.ServiceUOFBuilder()
                .setCrudService(service)
                .build();
    }

    private static ICrudService getCrudService(){
        return MockService.getCrudService();
    }

    private static GetBeanViewPageCommand getGetBeanViewPageCommand(IServiceUnitOfWork serviceUnitOfWork) {
        return new GetBeanViewPageCommand(serviceUnitOfWork);
    }

    private static List<Bean> getBeans() throws BeanException {
        return new ArrayList<Bean>(){
            {add(DefaultBean.getBean());}
        };
    }

    private static List<Dependency> getDependencies() {
        return new ArrayList<Dependency>(){
            {
                add(DefaultBean.getDependency());
            }
        };
    }

    private static BeanViewPageInfo getBeanViewPageInfo() {
        BeanViewPageInfo beanViewPageInfo = mock(BeanViewPageInfo.class);
        beanViewPageInfo.countRecords = 1;
        beanViewPageInfo.dependencyMap = new HashMap<Bean, List<Dependency>>();
        return beanViewPageInfo;
    }

    @Test
    public void checkGetBeanViewPage() throws CommandException, ServiceException, BeanException {
        Bean bean = DefaultBean.getBean();

        when(crudService.readTotalCount(anyString())).thenReturn(1);
        List<Bean> beans = getBeans();
        when(crudService.read(anyString(), anyInt(), anyInt())).thenReturn(beans);

        BeanViewPageInfo expectedBeanViewPageInfo = getBeanViewPageInfo();

        List<Dependency> dependencies = getDependencies();
        expectedBeanViewPageInfo.dependencyMap.put(bean, dependencies);
        when(crudService.readDependencies(anyString(), Matchers.eq(bean))).thenReturn(dependencies);

        assertEquals(getBeanViewPageCommand.execute(getBeanViewPageInfo()).dependencyMap, expectedBeanViewPageInfo.dependencyMap);
    }

    @Test(expected = CommandException.class)
    public void checkGetBeanViewPageException() throws CommandException, ServiceException {
        when(crudService.read(anyString(), anyInt(), anyInt())).thenThrow(Exception.class);
        getBeanViewPageCommand.execute(getBeanViewPageInfo());
        fail();
    }
}
