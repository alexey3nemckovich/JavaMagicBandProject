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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReadBeanServiceTest {
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

    public void readBeans(String tableName, int index, int count, List<Bean> beans) throws ServiceException, DaoException {
        when(crudDaoFactory.getCrud(tableName)).thenReturn(crudDao);
        final boolean isCreated = true;
        when(crudDao.read(index, count)).thenReturn(beans);

        for(int i = 0; i < 100; ++i) {
            List<Bean> beanList = crudService.read(tableName, index, count);
        }
    }

    @Test(timeout = 1000)
    public void checkCreatedDiscount() throws BeanException, ServiceException, DaoException {
        String tableName = "Discounts";
        Discount discount = mock(Discount.class);
        List<Bean> discountList = new ArrayList<Bean>(){
            {
                add(discount);
                add(discount);
                add(discount);
                add(discount);
                add(discount);
            }
        };
        readBeans(tableName, 0, 5, discountList);
    }

    @Test(timeout = 1000)
    public void checkCreatedDiscountUser() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "User discounts";
        DiscountUser discountUser = mock(DiscountUser.class);
        List<Bean> discountUserList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(discountUser);
                }
            }
        };
        readBeans(tableName, 0, count, discountUserList);
    }

    @Test(timeout = 1000)
    public void checkCreatedNotification() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Notifications";
        Notification notification = mock(Notification.class);
        List<Bean> notificationList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(notification);
                }
            }
        };
        readBeans(tableName, 0, count, notificationList);
    }

    @Test(timeout = 1000)
    public void checkCreatedOrder() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Orders";
        Order order = mock(Order.class);
        List<Bean> orderList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(order);
                }
            }
        };
        readBeans(tableName, 0, count, orderList);
    }

    @Test(timeout = 1000)
    public void checkCreatedOrderedService() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Ordered services";
        OrderedService orderedService = mock(OrderedService.class);
        List<Bean> orderedServiceList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(orderedService);
                }
            }
        };
        readBeans(tableName, 0, count, orderedServiceList);
    }

    @Test(timeout = 1000)
    public void checkCreatedOrderSparePart() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Order spare parts";
        OrderSparePart orderSparePart = mock(OrderSparePart.class);
        List<Bean> orderSparePartList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(orderSparePart);
                }
            }
        };
        readBeans(tableName, 0, count, orderSparePartList);
    }

    @Test(timeout = 1000)
    public void checkCreatedService() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Services";
        Service service = mock(Service.class);
        List<Bean> serviceList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(service);
                }
            }
        };
        readBeans(tableName, 0, count, serviceList);
    }

    @Test(timeout = 1000)
    public void checkCreatedServiceShop() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Service shops";
        ServiceShop serviceShop = mock(ServiceShop.class);
        List<Bean> serviceShopList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(serviceShop);
                }
            }
        };
        readBeans(tableName, 0, count, serviceShopList);
    }

    @Test(timeout = 1000)
    public void checkCreatedShare() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Shares";
        Share share = mock(Share.class);
        List<Bean> shareList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(share);
                }
            }
        };
        readBeans(tableName, 0, count, shareList);
    }

    @Test(timeout = 1000)
    public void checkCreatedShareDiscount() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Share discounts";
        ShareDiscount shareDiscount = mock(ShareDiscount.class);
        List<Bean> shareDiscountList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(shareDiscount);
                }
            }
        };
        readBeans(tableName, 0, count, shareDiscountList);
    }

    @Test(timeout = 1000)
    public void checkCreatedSparePart() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Spare parts";
        SparePart sparePart = mock(SparePart.class);
        List<Bean> sparePartList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(sparePart);
                }
            }
        };
        readBeans(tableName, 0, count, sparePartList);
    }

    @Test(timeout = 1000)
    public void checkCreatedStaff() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Staffs";
        Staff staff = mock(Staff.class);
        List<Bean> staffList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(staff);
                }
            }
        };
        readBeans(tableName, 0, count, staffList);
    }

    @Test(timeout = 1000)
    public void checkCreatedUser() throws BeanException, ServiceException, DaoException {
        int count = 5;
        String tableName = "Users";
        User user = mock(User.class);
        List<Bean> userList = new ArrayList<Bean>(){
            {
                for(int i = 0; i < count; ++i) {
                    add(user);
                }
            }
        };
        readBeans(tableName, 0, count, userList);
    }

    /*

        Testing throw ServiceException

     */

    @Test(expected = ServiceException.class)
    public void checkCreatedBeanException() throws ServiceException, DaoException{
        int count = 5;
        String tableName = "Beans";
        when(crudDaoFactory.getCrud(tableName)).thenReturn(crudDao);
        when(crudDao.read(0, count)).thenThrow(DaoException.class);
        crudService.read(tableName, 0, count);
        fail();
    }
}
