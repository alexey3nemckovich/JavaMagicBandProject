package unit.service.crud;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.bean.impl.*;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;
import main.com.bsuir.autoservice.dao.impl.crudfactory.ICrudDaoFactory;
import main.com.bsuir.autoservice.dao.impl.user.IUserDao;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.crud.CrudService;
import main.com.bsuir.autoservice.service.impl.crud.ICrudService;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteBeanServiceTest {
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

    public boolean deleteBean(String tableName, Bean bean) throws ServiceException, DaoException {
        when(crudDaoFactory.getCrud(tableName)).thenReturn(crudDao);
        final boolean isUpdated = true;
        when(crudDao.delete(bean)).thenReturn(isUpdated);
        return crudService.delete(tableName, bean);
    }

    @Test
    public void checkDeletedDiscount() throws BeanException, ServiceException, DaoException {
        String tableName = "Discounts";
        Discount discount = mock(Discount.class);
        assertTrue(deleteBean(tableName, discount));
    }

    @Test
    public void checkDeletedDiscountUser() throws BeanException, ServiceException, DaoException {
        String tableName = "User discounts";
        DiscountUser discountUser = mock(DiscountUser.class);
        assertTrue(deleteBean(tableName, discountUser));
    }

    @Test
    public void checkDeletedNotification() throws BeanException, ServiceException, DaoException {
        String tableName = "Notifications";
        Notification notification = mock(Notification.class);
        assertTrue(deleteBean(tableName, notification));
    }

    @Test
    public void checkDeletedOrder() throws BeanException, ServiceException, DaoException {
        String tableName = "Orders";
        Order order = mock(Order.class);
        assertTrue(deleteBean(tableName, order));
    }

    @Test
    public void checkDeletedOrderedService() throws BeanException, ServiceException, DaoException {
        String tableName = "Ordered services";
        OrderedService orderedService = mock(OrderedService.class);
        assertTrue(deleteBean(tableName, orderedService));
    }

    @Test
    public void checkDeletedOrderSparePart() throws BeanException, ServiceException, DaoException {
        String tableName = "Order spare parts";
        OrderSparePart orderSparePart = mock(OrderSparePart.class);
        assertTrue(deleteBean(tableName, orderSparePart));
    }

    @Test
    public void checkDeletedService() throws BeanException, ServiceException, DaoException {
        String tableName = "Services";
        Service service = mock(Service.class);
        assertTrue(deleteBean(tableName, service));
    }

    @Test
    public void checkDeletedServiceShop() throws BeanException, ServiceException, DaoException {
        String tableName = "Service shops";
        ServiceShop serviceShop = mock(ServiceShop.class);
        assertTrue(deleteBean(tableName, serviceShop));
    }

    @Test
    public void checkDeletedShare() throws BeanException, ServiceException, DaoException {
        String tableName = "Shares";
        Share share = mock(Share.class);
        assertTrue(deleteBean(tableName, share));
    }

    @Test
    public void checkDeletedShareDiscount() throws BeanException, ServiceException, DaoException {
        String tableName = "Share discounts";
        ShareDiscount shareDiscount = mock(ShareDiscount.class);
        assertTrue(deleteBean(tableName, shareDiscount));
    }

    @Test
    public void checkDeletedSparePart() throws BeanException, ServiceException, DaoException {
        String tableName = "Spare parts";
        SparePart sparePart = mock(SparePart.class);
        assertTrue(deleteBean(tableName, sparePart));
    }

    @Test
    public void checkDeletedStaff() throws BeanException, ServiceException, DaoException {
        String tableName = "Staffs";
        Staff staff = mock(Staff.class);
        assertTrue(deleteBean(tableName, staff));
    }

    @Test
    public void checkDeletedUser() throws BeanException, ServiceException, DaoException {
        String tableName = "Users";
        User user = mock(User.class);
        assertTrue(deleteBean(tableName, user));
    }

    /*

        Testing throw ServiceException

     */

    @Test(expected = ServiceException.class)
    public void checkCreatedBeanException() throws ServiceException, DaoException{
        String tableName = "Beans";
        Bean bean = mock(Bean.class);
        when(crudDaoFactory.getCrud(tableName)).thenReturn(crudDao);
        when(crudDao.delete(bean)).thenThrow(DaoException.class);
        crudService.delete(tableName, bean);
        fail();
    }
}
