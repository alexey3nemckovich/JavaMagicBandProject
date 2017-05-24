package unit.service.crud;

import general.bean.DefaultBean;
import jdk.nashorn.internal.runtime.ECMAException;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.bean.impl.*;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;
import main.com.bsuir.autoservice.dao.impl.crudfactory.ICrudDaoFactory;
import main.com.bsuir.autoservice.dao.impl.crudfactory.impl.CrudDaoFactory;
import main.com.bsuir.autoservice.dao.impl.user.IUserDao;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.crud.CrudService;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddBeanServiceTest {
    private ICrudDao crudDao;
    private ICrudDaoFactory crudDaoFactory;
    private ICrudService crudService;

    @Before
    public void beforeTest() {
        crudDao = mock(IUserDao.class);
        crudDaoFactory = mock(ICrudDaoFactory.class);
        crudService = getCrudService(crudDaoFactory);
    }

    private static ICrudService getCrudService(ICrudDaoFactory crudDaoFactory) {
        return new CrudService(crudDaoFactory);
    }

    public boolean createBean(String tableName, Bean bean) throws ServiceException, DaoException {
        when(crudDaoFactory.getCrud(tableName)).thenReturn(crudDao);
        final boolean isCreated = true;
        when(crudDao.insert(bean)).thenReturn(isCreated);
        return crudService.create(tableName, bean);
    }

    @Test
    public void checkCreatedDiscount() throws BeanException, ServiceException, DaoException {
        String tableName = "Discounts";
        Discount discount = mock(Discount.class);
        assertTrue(createBean(tableName, discount));
    }

    @Test
    public void checkCreatedDiscountUser() throws BeanException, ServiceException, DaoException {
        String tableName = "User discounts";
        DiscountUser discountUser = mock(DiscountUser.class);
        assertTrue(createBean(tableName, discountUser));
    }

    @Test
    public void checkCreatedNotification() throws BeanException, ServiceException, DaoException {
        String tableName = "Notifications";
        Notification notification = mock(Notification.class);
        assertTrue(createBean(tableName, notification));
    }

    @Test
    public void checkCreatedOrder() throws BeanException, ServiceException, DaoException {
        String tableName = "Orders";
        Order order = mock(Order.class);
        assertTrue(createBean(tableName, order));
    }

    @Test
    public void checkCreatedOrderedService() throws BeanException, ServiceException, DaoException {
        String tableName = "Ordered services";
        OrderedService orderedService = mock(OrderedService.class);
        assertTrue(createBean(tableName, orderedService));
    }

    @Test
    public void checkCreatedOrderSparePart() throws BeanException, ServiceException, DaoException {
        String tableName = "Order spare parts";
        OrderSparePart orderSparePart = mock(OrderSparePart.class);
        assertTrue(createBean(tableName, orderSparePart));
    }

    @Test
    public void checkCreatedService() throws BeanException, ServiceException, DaoException {
        String tableName = "Services";
        Service service = mock(Service.class);
        assertTrue(createBean(tableName, service));
    }

    @Test
    public void checkCreatedServiceShop() throws BeanException, ServiceException, DaoException {
        String tableName = "Service shops";
        ServiceShop serviceShop = mock(ServiceShop.class);
        assertTrue(createBean(tableName, serviceShop));
    }

    @Test
    public void checkCreatedShare() throws BeanException, ServiceException, DaoException {
        String tableName = "Shares";
        Share share = mock(Share.class);
        assertTrue(createBean(tableName, share));
    }

    @Test
    public void checkCreatedShareDiscount() throws BeanException, ServiceException, DaoException {
        String tableName = "Share discounts";
        ShareDiscount shareDiscount = mock(ShareDiscount.class);
        assertTrue(createBean(tableName, shareDiscount));
    }

    @Test
    public void checkCreatedSparePart() throws BeanException, ServiceException, DaoException {
        String tableName = "Spare parts";
        SparePart sparePart = mock(SparePart.class);
        assertTrue(createBean(tableName, sparePart));
    }

    @Test
    public void checkCreatedStaff() throws BeanException, ServiceException, DaoException {
        String tableName = "Staffs";
        Staff staff = mock(Staff.class);
        assertTrue(createBean(tableName, staff));
    }

    @Test
    public void checkCreatedUser() throws BeanException, ServiceException, DaoException {
        String tableName = "Users";
        User user = mock(User.class);
        assertTrue(createBean(tableName, user));
    }

    /*

        Testing throw ServiceException

     */

    @Test(expected = ServiceException.class)
    public void checkCreatedBeanException() throws ServiceException, DaoException{
        String tableName = "Beans";
        Bean bean = mock(Bean.class);
        when(crudDaoFactory.getCrud(tableName)).thenReturn(crudDao);
        when(crudDao.insert(bean)).thenThrow(DaoException.class);
        crudService.create(tableName, bean);
        fail();
    }

}
