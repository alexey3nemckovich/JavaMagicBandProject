package main.com.bsuir.notepads.dao.crud.impl.order;

import com.google.inject.Inject;
import main.com.bsuir.notepads.bean.impl.order;
import main.com.bsuir.notepads.dao.crud.AbstractDaoCrud;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.dao.exception.DaoException;
import main.com.bsuir.notepads.dao.sql.ISql;
import main.com.bsuir.notepads.library.type.date.SimpleDate;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class OrderDao extends AbstractDaoCrud<Integer, order> implements IOrderDao{

    @Inject
    public OrderDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<order> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<order> result = new LinkedList<>();
        try {
            while (rs.next()) {
                order bean = new order();

                bean.setId(rs.getInt("id"));
                bean.setId_customer(rs.getInt("id_customer"));
                bean.setId_driver(rs.getInt("id_driver"));
                bean.setId_address(rs.getInt("id_address"));
                bean.setId_order_status(rs.getInt("id_order_status"));
                bean.setTotal_sum(rs.getInt("total_sum"));
                bean.setDate(new SimpleDate(rs.getString("date")));

                result.add(bean);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "order";
}
