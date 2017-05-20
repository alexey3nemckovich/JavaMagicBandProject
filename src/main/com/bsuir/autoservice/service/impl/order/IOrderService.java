package main.com.bsuir.autoservice.service.impl.order;

import main.com.bsuir.autoservice.bean.impl.Order;
import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.command.param.MechanicViewOrdersInfo;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;


public interface IOrderService extends IService {
    boolean makeOrder(Integer userId, List<Service> orderServices) throws ServiceException;
    List<Order> getUserOrders(int userId, int currentGroup, int elementCount) throws ServiceException;
    List<Order> getOrderServices(Integer userId, int detailOrderId) throws ServiceException;
    List<Order> getServiceShopOrders(int staffId, MechanicViewOrdersInfo.SortedType orderSortType,
                                     int orderPage, int orderCount) throws ServiceException;
    boolean changeOrderState(int changedStaffId, int orderId, Order.State newOrderState) throws ServiceException;
    boolean addOrderNotification(int staffWriterId, int orderId, String notificationMessage) throws ServiceException;
}
