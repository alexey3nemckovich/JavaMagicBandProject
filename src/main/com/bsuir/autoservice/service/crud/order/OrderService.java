package main.com.bsuir.autoservice.service.crud.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.order;
import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.command.param.MechanicViewOrdersInfo;
import main.com.bsuir.autoservice.dao.unitOfWork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.crud.AbstractServiceCrud;
import main.com.bsuir.autoservice.service.crud.IOrderService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;

public class OrderService extends AbstractServiceCrud<Integer,order> implements IOrderService {
    private final IDaoUnitOfWork daoUnitOfWork;

    @Inject
    public OrderService(IDaoUnitOfWork daoUnitOfWork) {
        super(daoUnitOfWork.getOrderDao());
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public boolean makeOrder(Integer userId, List<service> orderServices) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<order> getUserOrders(int userId, int currentGroup, int elementCount) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<order> getOrderServices(Integer userId, int detailOrderId) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<order> getServiceShopOrders(int staffServiceShopId, MechanicViewOrdersInfo.SortedType orderSortType,
                                            int orderPage, int orderCount) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean changeOrderState(int changedStaffId, int orderId, order.State newOrderState) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addOrderNotification(int staffWriterId, int orderId, String notificationMessage) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
