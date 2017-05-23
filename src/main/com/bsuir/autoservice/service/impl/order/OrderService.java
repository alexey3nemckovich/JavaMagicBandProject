package main.com.bsuir.autoservice.service.impl.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Order;
import main.com.bsuir.autoservice.command.param.MechanicViewOrdersInfo;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public class OrderService implements IOrderService {

    @Inject
    public OrderService(IDaoUnitOfWork daoUnitOfWork) {
        this.daoUnitOfWork = daoUnitOfWork;
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

    @Override
    public boolean makeOrder(int userId, List<Integer> orderServices, int serviceShopId) throws ServiceException {
        try{
            return daoUnitOfWork.getOrderedServiceDao().insertAll(
                    daoUnitOfWork.getOrderDao().makeOrder(userId, serviceShopId), orderServices);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    private final IDaoUnitOfWork daoUnitOfWork;
}
