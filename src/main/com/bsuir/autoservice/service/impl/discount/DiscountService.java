package main.com.bsuir.autoservice.service.impl.discount;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;

public class DiscountService implements IDiscountService {

    @Inject
    public DiscountService(IDaoUnitOfWork daoUnitOfWork){
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
