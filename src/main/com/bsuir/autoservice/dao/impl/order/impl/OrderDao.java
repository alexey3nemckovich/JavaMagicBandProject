package main.com.bsuir.autoservice.dao.impl.order.impl;

import main.com.bsuir.autoservice.bean.Order;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.impl.order.IOrderDao;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.util.List;

public class OrderDao extends AbstractDaoCrud<Order, Integer> implements IOrderDao {
    private static final String tableName = "order";
    private static final String primaryKeyName = "id";

    @Override
    public String getTableNameImpl() {
        return tableName;
    }

    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    @Override
    public List<Order> parseResultSet(ResultSet rs) throws DaoException{
        try {
            throw new NotImplementedException();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}