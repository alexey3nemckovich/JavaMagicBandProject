package main.com.bsuir.autoservice.dao.impl.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Order;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.ISql;
import main.com.bsuir.autoservice.library.type.date.SimpleDate;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class OrderDao extends AbstractCrudDao<Integer, Order> implements IOrderDao {

    @Inject
    public OrderDao(IDatabase db, ISql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Order> parseResultSet(ResultSet rs) throws DaoException{
        LinkedList<Order> result = new LinkedList<>();
        try {
            while (rs.next()){
                Order bean = new Order();
                bean.setId(rs.getInt("id"));
                bean.setUserId(rs.getInt("user_id"));
                bean.setServiceShopId(rs.getInt("service_shop_id"));
                bean.setDateOpen(new SimpleDate(rs.getString("date_open")));
                bean.setDateClose(new SimpleDate(rs.getString("date_close")));
                bean.setSum(rs.getInt("sum"));
                bean.setState(Order.State.valueOf(rs.getString("state")));
                result.add(bean);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }
}
