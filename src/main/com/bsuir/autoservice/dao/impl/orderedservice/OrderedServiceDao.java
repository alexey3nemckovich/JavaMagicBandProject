package main.com.bsuir.autoservice.dao.impl.orderedservice;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.OrderedService;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import javax.lang.model.type.NullType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderedServiceDao extends AbstractCrudDao<NullType, OrderedService> implements IOrderedServiceDao {

    @Inject
    public OrderedServiceDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<OrderedService> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<OrderedService> result = new LinkedList<>();
        try {
            while (rs.next()) {
                OrderedService bean = new OrderedService();
                bean.setServiceId(rs.getInt("service_id"));
                bean.setOrderId(rs.getInt("order_id"));
                bean.setDate(rs.getDate("date"));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
