package unit.command.crud.get;

import general.bean.DefaultBean;
import general.bean.MockBean;
import general.service.MockService;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.command.crud.get.GetBeanDependencyViewPageCommand;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.command.param.BeanDependencyViewPageInfo;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import main.com.bsuir.autoservice.service.unitofwork.IServiceUnitOfWork;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetBeanDependencyViewPageCommandTest {
    ICrudService crudService;
    GetBeanDependencyViewPageCommand getBeanDependencyViewPageCommand;

    @Before
    public void beforeTest() {
        crudService = getCrudService();
        IServiceUnitOfWork serviceUOF = getServiceUOF(crudService);
        getBeanDependencyViewPageCommand = getGetBeanDependencyViewPageCommand(serviceUOF);
    }

    private static IServiceUnitOfWork getServiceUOF(ICrudService service){
        return new MockService.ServiceUOFBuilder()
                .setCrudService(service)
                .build();
    }

    private static ICrudService getCrudService(){
        return MockService.getCrudService();
    }

    private static GetBeanDependencyViewPageCommand getGetBeanDependencyViewPageCommand(IServiceUnitOfWork serviceUnitOfWork) {
        return new GetBeanDependencyViewPageCommand(serviceUnitOfWork);
    }

    private static List<Bean> getDependencyBeans() throws BeanException {
        return new ArrayList<Bean>(){
            {add(DefaultBean.getUser());}
        };
    }

    private static List<Dependency> getDependencies() {
        return new ArrayList<Dependency>(){
            {
                add(DefaultBean.getDependency());
            }
        };
    }

    private static BeanDependencyViewPageInfo getBeanDependencyViewPageInfo() {
        BeanDependencyViewPageInfo beanDependencyViewPageInfo = mock(BeanDependencyViewPageInfo.class);
        beanDependencyViewPageInfo.dependency = mock(Dependency.class);
        beanDependencyViewPageInfo.dependencyMap = new HashMap<Bean, List<Dependency>>();
        beanDependencyViewPageInfo.countRecords = 1;
        return beanDependencyViewPageInfo;
    }

    @Test
    public void checkGetBeanDependencyViewPage() throws CommandException, ServiceException, BeanException {
        Bean bean = DefaultBean.getUser();

        List<Bean> dependencyBeans = getDependencyBeans();
        when(crudService.read(anyString(), anyMap())).thenReturn(dependencyBeans);
        List<Dependency> dependencies = getDependencies();
        when(crudService.readDependencies(anyString(), Matchers.eq(bean))).thenReturn(dependencies);

        BeanDependencyViewPageInfo expectedBeanDependencyViewPageInfo = getBeanDependencyViewPageInfo();
        expectedBeanDependencyViewPageInfo.dependencyMap.put(bean, dependencies);
        assertEquals(getBeanDependencyViewPageCommand.execute(getBeanDependencyViewPageInfo()).dependencyMap, expectedBeanDependencyViewPageInfo.dependencyMap);
    }

    @Test(expected = CommandException.class)
    public void checkGetBeanDependencyViewPageException() throws CommandException, ServiceException {
        when(crudService.read(anyString(), anyMap())).thenThrow(Exception.class);
        getBeanDependencyViewPageCommand.execute(getBeanDependencyViewPageInfo());
        fail();
    }

}
