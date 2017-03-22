package main.com.bsuir.autoservice.dao.unitOfWork.impl;

import main.com.bsuir.autoservice.dao.impl.orderDao.IOrderDao;
import main.com.bsuir.autoservice.dao.impl.userDao.IUserDao;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;

public class DefaultDaoUnitOfWork implements IDaoUnitOfWork {
    private final IUserDao userDao;
    private final IOrderDao orderDao;

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
