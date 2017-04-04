package main.com.bsuir.autoservice.dao.unitOfWork;

import main.com.bsuir.autoservice.dao.crud.order.IOrderDao;
import main.com.bsuir.autoservice.dao.crud.staff.IStaffDao;
import main.com.bsuir.autoservice.dao.crud.user.IUserDao;

public interface IDaoUnitOfWork {
   IUserDao getUserDao();
   IOrderDao getOrderDao();
   IStaffDao getStaffDao();
}
