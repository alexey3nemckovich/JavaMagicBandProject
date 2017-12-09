package main.com.bsuir.notepads.service.crud.impl.registration;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.registration;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class RegistrationService extends AbstractServiceCrud<Integer, registration> implements IRegistrationService {

    @Inject
    public RegistrationService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getRegistrationDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}