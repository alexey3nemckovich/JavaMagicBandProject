package main.com.bsuir.autoservice.dao.impl.orderedservice;

import main.com.bsuir.autoservice.bean.impl.OrderedService;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;

import javax.lang.model.type.NullType;
import java.util.List;

public interface IOrderedServiceDao extends ICrudDao<NullType, OrderedService> {
    boolean insertAll(int orderId, List<Integer> orderServices) throws DaoException;
}
