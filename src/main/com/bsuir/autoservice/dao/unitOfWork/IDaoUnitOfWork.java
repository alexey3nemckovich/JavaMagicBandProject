package main.com.bsuir.autoservice.dao.unitOfWork;

import main.com.bsuir.autoservice.dao.impl.UserDao.IUserDaoController;
import main.com.bsuir.autoservice.service.impl.userService.IServiceUserService;

public interface IDaoUnitOfWork {
   IUserDaoController getUserDao();
}
