package main.com.bsuir.autoservice.service.impl.crud.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.exception.ServiceException;
import main.com.bsuir.autoservice.service.impl.IUserService;
import main.com.bsuir.autoservice.service.impl.crud.AbstractServiceCrud;

public class UserService extends AbstractServiceCrud<Integer, User> implements IUserService {
    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public UserService(IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public boolean checkLogin(String login, String password) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean resetLogin(String email) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getGeneralInformation(int userId) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateUserInformation(int userId, User newUser) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
