package main.com.bsuir.autoservice.service.crud.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.IUserService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

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
}
