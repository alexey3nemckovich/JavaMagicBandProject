package main.com.bsuir.autoservice.service.crud.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.IOrderService;

public class OrderService extends AbstractServiceCrud<Integer,order> implements IOrderService {
    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public OrderService(IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }
}
