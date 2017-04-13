package main.com.bsuir.autoservice.service.crud.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;

public class OrderService extends AbstractServiceCrud<Integer,order> implements IOrderService {
    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public OrderService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }
}
