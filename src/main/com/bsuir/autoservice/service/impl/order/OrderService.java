package main.com.bsuir.autoservice.service.impl.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Order;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.command.param.MechanicViewOrdersInfo;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public class OrderService implements IOrderService {

    @Inject
    public OrderService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
    }

    @Override
    public boolean makeOrder(Integer userId, List<Service> orderServices) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getUserOrders(int userId, int currentGroup, int elementCount) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getOrderServices(Integer userId, int detailOrderId) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getServiceShopOrders(int staffServiceShopId, MechanicViewOrdersInfo.SortedType orderSortType,
                                            int orderPage, int orderCount) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean changeOrderState(int changedStaffId, int orderId, Order.State newOrderState) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addOrderNotification(int staffWriterId, int orderId, String notificationMessage) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
