package main.com.bsuir.autoservice.dao.impl.notification;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Notification;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationDao extends AbstractCrudDao<Integer, Notification> implements INotificationDao {

    private static final String NOTIFICATION_ID = "id";
    private static final String NOTIFICATION_ORDER_ID = "order_id";
    private static final String NOTIFICATION_STAFF_ID = "staff_id";
    private static final String NOTIFICATION_DATE = "date";
    private static final String NOTIFICATION_CONTENT = "content";
    private static final String NOTIFICATION_STATE = "state";

    @Inject
    public NotificationDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Notification> parseResultSet(ResultSet rs) throws SQLException {
        return new ArrayList<Notification>() {{
            while (rs.next()) {
                Notification bean = new Notification();
                bean.setId(rs.getInt(NOTIFICATION_ID));
                bean.setOrderId(rs.getInt(NOTIFICATION_ORDER_ID));
                bean.setStaffId(rs.getInt(NOTIFICATION_STAFF_ID));
                bean.setDate(rs.getDate(NOTIFICATION_DATE));
                bean.setContent(rs.getString(NOTIFICATION_CONTENT));
                bean.setState(Notification.State.valueOf(rs.getString(NOTIFICATION_STATE)));
                add(bean);
            }
        }};
    }

    @Override
    public boolean haveNewNotification() throws DaoException {
        final Map<String, String> whereConditions = new HashMap<String, String>() {{
            put(NOTIFICATION_STATE, String.valueOf(Notification.State.UNCONFIRMED));
        }};

        final String namedExists = "haveNotification";

        return executeQuery(rs -> {
                    rs.next();
                    return rs.getInt(namedExists) != 0;
                },
                sql.getExistsStatement(getTableName(), whereConditions, namedExists));
    }
}
