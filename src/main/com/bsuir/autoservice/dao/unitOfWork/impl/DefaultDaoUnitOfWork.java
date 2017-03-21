package main.com.bsuir.autoservice.dao.unitOfWork.impl;

import main.com.bsuir.autoservice.dao.impl.UserDao.IUserDaoController;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.impl.userService.IServiceUserService;

public class DefaultDaoUnitOfWork implements IDaoUnitOfWork {
    IUserDaoController userDao;

    public DefaultDaoUnitOfWork(IUserDaoController userDao) {
        this.userDao = userDao;
    }

    @Override
    public IUserDaoController getUserDao() {
        return userDao;
    }
}
