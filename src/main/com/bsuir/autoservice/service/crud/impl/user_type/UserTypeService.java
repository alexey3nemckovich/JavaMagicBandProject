package main.com.bsuir.autoservice.service.crud.impl.user_type;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.user_type;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class UserTypeService extends AbstractServiceCrud<Integer, user_type> implements IUserTypeService {

    @Inject
    public UserTypeService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getUserTypeDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
