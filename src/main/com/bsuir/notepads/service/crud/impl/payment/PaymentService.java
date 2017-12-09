package main.com.bsuir.notepads.service.crud.impl.payment;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.payment;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class PaymentService extends AbstractServiceCrud<Integer, payment> implements IPaymentService {

    @Inject
    public PaymentService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getPaymentDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
