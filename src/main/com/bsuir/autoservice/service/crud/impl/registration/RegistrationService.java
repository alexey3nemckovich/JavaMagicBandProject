package main.com.bsuir.autoservice.service.crud.impl.registration;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.registration;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class RegistrationService extends AbstractServiceCrud<Integer, registration> implements IRegistrationService {

    @Inject
    public RegistrationService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getRegistrationDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
