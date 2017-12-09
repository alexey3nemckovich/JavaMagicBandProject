package main.com.bsuir.notepads.service.crud.impl.phone_operator;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.phone_operator;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class PhoneOperatorService extends AbstractServiceCrud<Integer, phone_operator> implements IPhoneOperatorService {

    @Inject
    public PhoneOperatorService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getPhoneOperatorDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
