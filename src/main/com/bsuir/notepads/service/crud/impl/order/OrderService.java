package main.com.bsuir.notepads.service.crud.impl.order;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.order;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class OrderService extends AbstractServiceCrud<Integer, order> implements IOrderService {

    @Inject
    public OrderService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
