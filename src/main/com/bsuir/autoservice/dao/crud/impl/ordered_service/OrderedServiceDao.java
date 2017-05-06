package main.com.bsuir.autoservice.dao.crud.impl.ordered_service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.ordered_service;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderedServiceDao extends AbstractDaoCrud<Integer, ordered_service> implements IOrderedServiceDao {

    @Inject
    public OrderedServiceDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<ordered_service> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<ordered_service> result = new LinkedList<>();
        try {
            while (rs.next()) {
                ordered_service bean = new ordered_service();
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

    private final String tableName = "ordered_service";
}
