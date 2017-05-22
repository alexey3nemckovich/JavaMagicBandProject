package main.com.bsuir.autoservice.dao.impl.ordersparepart;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.OrderSparePart;
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

public class OrderSparePartDao extends AbstractCrudDao<NullType, OrderSparePart> implements IOrderSparePartDao{

    @Inject
    public OrderSparePartDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<OrderSparePart> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<OrderSparePart> result = new LinkedList<>();
        try {
            while (rs.next()) {
                OrderSparePart bean = new OrderSparePart();
                bean.setSparePartId(rs.getInt("spare_part_id"));
                bean.setOrderId(rs.getInt("order_id"));
                bean.setCount(rs.getInt("count"));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
