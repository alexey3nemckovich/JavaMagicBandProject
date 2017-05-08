package main.com.bsuir.autoservice.service.crud;

import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;

public interface IOrderService extends IServiceCrud<Integer, order> {
    boolean makeOrder(Integer userId, List<service> orderServices) throws ServiceException;
}
