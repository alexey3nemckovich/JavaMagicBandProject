package main.com.bsuir.autoservice.dao.unitOfWork.impl;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.dao.impl.order.IOrderDao;
import main.com.bsuir.autoservice.dao.impl.user.IUserDao;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;

public class DefaultDaoUnitOfWork implements IDaoUnitOfWork {
    private final IUserDao userDao;
    private final IOrderDao orderDao;

    @Inject
    public DefaultDaoUnitOfWork(IUserDao userDao, IOrderDao orderDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }

    @Override
    public IOrderDao getOrderDao() {
        return orderDao;
    }
}
