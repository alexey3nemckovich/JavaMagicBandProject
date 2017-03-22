package main.com.bsuir.autoservice.service.impl.user.impl;

import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.impl.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.impl.user.IUserService;

public class UserService extends AbstractServiceCrud<Integer,User> implements IUserService {
    private final IDaoUnitOfWork daoUnitOfWork;

    public UserService(IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }
}
