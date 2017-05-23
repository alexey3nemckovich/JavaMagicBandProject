package main.com.bsuir.autoservice.dao.impl.orderedservice;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.OrderedService;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;
import main.com.bsuir.autoservice.library.type.date.SimpleDate;

import javax.lang.model.type.NullType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderedServiceDao extends AbstractCrudDao<NullType, OrderedService> implements IOrderedServiceDao {

    private static final String SERVICE_ID = "service_id";
    private static final String ORDER_ID = "order_id";
    private static final String SERVICE_ORDER_DATE = "date";

    @Inject
    public OrderedServiceDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<OrderedService> parseResultSet(ResultSet rs) throws SQLException {
        return new ArrayList<OrderedService>() {{
            while (rs.next()) {
                OrderedService bean = new OrderedService();
                bean.setServiceId(rs.getInt(SERVICE_ID));
                bean.setOrderId(rs.getInt(ORDER_ID));
                bean.setDate(rs.getDate(SERVICE_ORDER_DATE));
                add(bean);
            }
        }};
    }

    @Override
    public boolean insertAll(int orderId, List<Integer> orderServices) throws DaoException {
        OrderedService temp = new OrderedService();
        temp.setOrderId(orderId);
        temp.setDate(new SimpleDate());

        for (int orderService : orderServices){
            temp.setServiceId(orderService);
            insert(temp);
        }
        return true;
    }
}
