package main.com.bsuir.autoservice.service.crud.user;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.User;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;

public class UserService extends AbstractServiceCrud<Integer, User> implements IUserService {
    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public UserService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }
}
