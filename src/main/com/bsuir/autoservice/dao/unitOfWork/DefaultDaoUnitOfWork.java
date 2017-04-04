package main.com.bsuir.autoservice.dao.unitOfWork;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.dao.order.IOrderDao;
import main.com.bsuir.autoservice.dao.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.user.IUserDao;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;

public class DefaultDaoUnitOfWork implements IDaoUnitOfWork {
    private final IUserDao userDao;
    private final IOrderDao orderDao;
    private final IStaffDao staffDao;

    @Inject
    public DefaultDaoUnitOfWork(Injector injector) {
        userDao = injector.getInstance(IUserDao.class);
        orderDao = injector.getInstance(IOrderDao.class);
        staffDao = injector.getInstance(IStaffDao.class);
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public IOrderDao getOrderDao() {
        return orderDao;
    }

    @Override
    public IStaffDao getStaffDao(){return staffDao;}
}
