package main.com.bsuir.notepads.service.crud.impl.order_status;

import com.google.inject.Inject;


import main.com.bsuir.notepads.bean.impl.order_status;
import main.com.bsuir.notepads.binding.annotation.Default;
import main.com.bsuir.notepads.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.notepads.service.crud.AbstractServiceCrud;

public class OrderStatusService extends AbstractServiceCrud<Integer, order_status> implements IOrderStatusService {

    @Inject
    public OrderStatusService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderStatusDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
