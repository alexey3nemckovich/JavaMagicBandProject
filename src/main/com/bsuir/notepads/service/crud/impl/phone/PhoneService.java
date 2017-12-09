package main.com.bsuir.notepads.service.crud.impl.phone;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.phone;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class PhoneService extends AbstractServiceCrud<Integer, phone> implements IPhoneService {

    @Inject
    public PhoneService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getPhoneDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
