package main.com.bsuir.autoservice.dao.impl.order;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Order;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;
import main.com.bsuir.autoservice.library.type.date.SimpleDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao extends AbstractCrudDao<Integer, Order> implements IOrderDao {

    private static final String ORDER_ID = "id";
    private static final String ORDER_USER_ID = "user_id";
    private static final String ORDER_SERVICE_SHOP_ID = "service_shop_id";
    private static final String ORDER_DATE_OPEN = "date_open";
    private static final String ORDER_DATE_CLOSE = "date_close";
    private static final String ORDER_SUM = "sum";
    private static final String ORDER_STATE = "state";

    @Inject
    public OrderDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Order> parseResultSet(ResultSet rs) throws SQLException {
        try {
            return new ArrayList<Order>() {{
                while (rs.next()) {
                    Order bean = new Order();
                    bean.setId(rs.getInt(ORDER_ID));
                    bean.setUserId(rs.getInt(ORDER_USER_ID));
                    bean.setServiceShopId(rs.getInt(ORDER_SERVICE_SHOP_ID));
                    bean.setDateOpen(new SimpleDate(rs.getString(ORDER_DATE_OPEN)));
                    bean.setDateClose(new SimpleDate(rs.getString(ORDER_DATE_CLOSE)));
                    bean.setSum(rs.getInt(ORDER_SUM));
                    bean.setState(Order.State.valueOf(rs.getString(ORDER_STATE)));
                    add(bean);
                }
            }};
        } catch (ParseException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public int makeOrder(int userId, int serviceShopId) throws DaoException {
            Order order = new Order();
            order.setServiceShopId(serviceShopId);
            order.setDateOpen(new SimpleDate());
            order.setUserId(userId);
            order.setState(Order.State.QUEUED);
            order.setSum(0);

            insert(order);

            return getLastPrimaryKey();
    }

    @Override
    public List<Order> getUserPartOrders(int userId, int currentGroup, int elementCount) throws DaoException {
        final Map<String, String> whereConditions = new HashMap<String, String>() {{
            put(ORDER_USER_ID, String.valueOf(userId));
        }};

        return executeQuery( this::parseResultSet,
                sql.getSelectWhereRangeQuery(getTableName(), whereConditions, currentGroup, elementCount));
    }
}
