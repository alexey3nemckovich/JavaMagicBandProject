package main.com.bsuir.autoservice.service.crud.impl.order_status;

import com.google.inject.Inject;


import main.com.bsuir.autoservice.bean.impl.order_status;
import main.com.bsuir.autoservice.binding.annotation.Default;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;


import java.util.ArrayList;
import java.util.List;

public class OrderStatusService extends AbstractServiceCrud<Integer, order_status> implements IOrderStatusService {

    @Inject
    public OrderStatusService(@Default IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderStatusDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }



    private final IDaoUnitOfWork daoUnitOfWork;
}
