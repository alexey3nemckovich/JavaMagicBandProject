package main.com.bsuir.notepads.service.crud.impl.user;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.user;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class UserService extends AbstractServiceCrud<Integer, user> implements IUserService {

    @Inject
    public UserService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
