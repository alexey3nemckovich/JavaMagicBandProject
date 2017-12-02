package main.com.bsuir.autoservice.dao.crud.impl.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.backup.order;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;
import main.com.bsuir.autoservice.library.type.date.SimpleDate;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class OrderDao extends AbstractDaoCrud<Integer, order> implements IOrderDao {

    @Inject
    public OrderDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<order> parseResultSet(ResultSet rs) throws DaoException{
        LinkedList<order> result = new LinkedList<>();
        try {
            while (rs.next()){
                order bean = new order();
                bean.setId(rs.getInt("id"));
                bean.setUserId(rs.getInt("user_id"));
                bean.setServiceShopId(rs.getInt("service_shop_id"));
                bean.setDateOpen(new SimpleDate(rs.getString("date_open")));
                bean.setDateClose(new SimpleDate(rs.getString("date_close")));
                bean.setSum(rs.getInt("sum"));
                bean.setState(order.State.valueOf(rs.getString("state")));
                result.add(bean);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    private static final String tableName = "order";
}
