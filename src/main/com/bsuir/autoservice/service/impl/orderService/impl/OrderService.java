package main.com.bsuir.autoservice.service.impl.orderService.impl;

import main.com.bsuir.autoservice.bean.Order;
import main.com.bsuir.autoservice.dao.DaoController;
import main.com.bsuir.autoservice.service.impl.orderService.IOrderService;
import main.com.bsuir.autoservice.service.impl.serviceCrud.AbstractServiceCrud;

public class OrderService extends AbstractServiceCrud<Integer,Order> implements IOrderService {
    public OrderService(DaoController daoController) {
        super(daoController);
    }
}
