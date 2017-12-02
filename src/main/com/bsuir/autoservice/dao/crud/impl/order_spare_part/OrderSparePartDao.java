package main.com.bsuir.autoservice.dao.crud.impl.order_spare_part;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.backup.order_spare_part;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderSparePartDao extends AbstractDaoCrud<Integer, order_spare_part> implements IOrderSparePartDao{

    @Inject
    public OrderSparePartDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<order_spare_part> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<order_spare_part> result = new LinkedList<>();
        try {
            while (rs.next()) {
                order_spare_part bean = new order_spare_part();
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

    private final String tableName = "order_spare_part";
}
