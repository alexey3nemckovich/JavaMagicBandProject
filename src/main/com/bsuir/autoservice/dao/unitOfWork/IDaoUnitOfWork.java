package main.com.bsuir.autoservice.dao.unitOfWork;

import main.com.bsuir.autoservice.dao.impl.order.IOrderDao;
import main.com.bsuir.autoservice.dao.impl.user.IUserDao;

public interface IDaoUnitOfWork {
   IUserDao getUserDao();
   IOrderDao getOrderDao();
}
