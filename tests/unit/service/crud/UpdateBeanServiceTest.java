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

public class UpdateBeanServiceTest {
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

    public boolean updateBean(String tableName, Bean bean, Map<String, String> conditionValues) throws ServiceException, DaoException {
        when(crudDaoFactory.getCrud(tableName)).thenReturn(crudDao);
        final boolean isUpdated = true;
        when(crudDao.update(bean, conditionValues)).thenReturn(isUpdated);
        return crudService.update(tableName, bean, conditionValues);
    }

    @Test
    public void checkUpdatedDiscount() throws BeanException, ServiceException, DaoException {
        String tableName = "Discounts";
        Discount discount = mock(Discount.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, discount, conditionValues));
    }

    @Test
    public void checkUpdatedDiscountUser() throws BeanException, ServiceException, DaoException {
        String tableName = "User discounts";
        DiscountUser discountUser = mock(DiscountUser.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, discountUser, conditionValues));
    }

    @Test
    public void checkUpdatedNotification() throws BeanException, ServiceException, DaoException {
        String tableName = "Notifications";
        Notification notification = mock(Notification.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, notification, conditionValues));
    }

    @Test
    public void checkUpdatedOrder() throws BeanException, ServiceException, DaoException {
        String tableName = "Orders";
        Order order = mock(Order.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, order, conditionValues));
    }

    @Test
    public void checkUpdatedOrderedService() throws BeanException, ServiceException, DaoException {
        String tableName = "Ordered services";
        OrderedService orderedService = mock(OrderedService.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, orderedService, conditionValues));
    }

    @Test
    public void checkUpdatedOrderSparePart() throws BeanException, ServiceException, DaoException {
        String tableName = "Order spare parts";
        OrderSparePart orderSparePart = mock(OrderSparePart.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, orderSparePart, conditionValues));
    }

    @Test
    public void checkUpdatedService() throws BeanException, ServiceException, DaoException {
        String tableName = "Services";
        Service service = mock(Service.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, service, conditionValues));
    }

    @Test
    public void checkUpdatedServiceShop() throws BeanException, ServiceException, DaoException {
        String tableName = "Service shops";
        ServiceShop serviceShop = mock(ServiceShop.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, serviceShop, conditionValues));
    }

    @Test
    public void checkUpdatedShare() throws BeanException, ServiceException, DaoException {
        String tableName = "Shares";
        Share share = mock(Share.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, share, conditionValues));
    }

    @Test
    public void checkUpdatedShareDiscount() throws BeanException, ServiceException, DaoException {
        String tableName = "Share discounts";
        ShareDiscount shareDiscount = mock(ShareDiscount.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, shareDiscount, conditionValues));
    }

    @Test
    public void checkUpdatedSparePart() throws BeanException, ServiceException, DaoException {
        String tableName = "Spare parts";
        SparePart sparePart = mock(SparePart.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, sparePart, conditionValues));
    }

    @Test
    public void checkUpdatedStaff() throws BeanException, ServiceException, DaoException {
        String tableName = "Staffs";
        Staff staff = mock(Staff.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, staff, conditionValues));
    }

    @Test
    public void checkUpdatedUser() throws BeanException, ServiceException, DaoException {
        String tableName = "Users";
        User user = mock(User.class);
        Map<String, String> conditionValues = mock(Map.class);
        assertTrue(updateBean(tableName, user, conditionValues));
    }

    /*

        Testing throw ServiceException

     */

    @Test(expected = ServiceException.class)
    public void checkCreatedBeanException() throws ServiceException, DaoException{
        String tableName = "Beans";
        Bean bean = mock(Bean.class);
        Map<String, String> conditionValues = mock(Map.class);
        when(crudDaoFactory.getCrud(tableName)).thenReturn(crudDao);
        when(crudDao.update(bean, conditionValues)).thenThrow(DaoException.class);
        crudService.update(tableName, bean, conditionValues);
        fail();
    }
}
