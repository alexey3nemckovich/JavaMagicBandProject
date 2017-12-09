package main.com.bsuir.notepads.service.crud.impl.user_type;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.user_type;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class UserTypeService extends AbstractServiceCrud<Integer, user_type> implements IUserTypeService {

    @Inject
    public UserTypeService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserTypeDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
