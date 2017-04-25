package main.com.bsuir.autoservice.service.crud.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.user;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

public class UserService extends AbstractServiceCrud<Integer, user> implements IUserService {
    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public UserService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public Boolean checkLogin(String login, String password) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean resetLogin(String email) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
