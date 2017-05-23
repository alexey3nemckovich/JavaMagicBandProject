package main.com.bsuir.autoservice.dao.impl.order;

import main.com.bsuir.autoservice.bean.impl.Order;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;

public interface IOrderDao extends ICrudDao<Integer, Order> {
    int makeOrder(int userId, int serviceShopId) throws DaoException;
}
