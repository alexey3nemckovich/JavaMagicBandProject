package main.com.bsuir.autoservice.dao.unitOfWork;

import main.com.bsuir.autoservice.dao.order.IOrderDao;
import main.com.bsuir.autoservice.dao.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.user.IUserDao;

public interface IDaoUnitOfWork {
   IUserDao getUserDao();
   IOrderDao getOrderDao();
   IStaffDao getStaffDao();
}
