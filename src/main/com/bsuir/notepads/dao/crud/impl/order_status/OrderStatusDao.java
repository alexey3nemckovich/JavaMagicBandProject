package main.com.bsuir.notepads.dao.crud.impl.order_status;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.order_status;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderStatusDao extends AbstractDaoCrud<Integer, order_status> implements IOrderStatusDao{

    @Inject
    public OrderStatusDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<order_status> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<order_status> result = new LinkedList<>();
        try {
            while (rs.next()) {
                order_status bean = new order_status();

                bean.setId(rs.getInt("id"));
                bean.setValue(rs.getString("value"));

                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "order_status";
}
