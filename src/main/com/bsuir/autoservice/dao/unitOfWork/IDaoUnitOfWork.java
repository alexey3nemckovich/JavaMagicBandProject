package main.com.bsuir.autoservice.dao.unitOfWork;

import main.com.bsuir.autoservice.dao.impl.orderDao.IOrderDao;
import main.com.bsuir.autoservice.dao.impl.userDao.IUserDao;

public interface IDaoUnitOfWork {
   IUserDao getUserDao();
   IOrderDao getOrderDao();
}
