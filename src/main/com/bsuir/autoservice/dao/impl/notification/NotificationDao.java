package main.com.bsuir.autoservice.dao.impl.notification;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Notification;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.AbstractCrudDao;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class NotificationDao extends AbstractCrudDao<Integer, Notification> implements INotificationDao {

    @Inject
    public NotificationDao(IDatabase db, ISql sql, IDatabaseMap databaseMap) {
        super(db, sql, databaseMap);
    }

    @Override
    public List<Notification> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Notification> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Notification bean = new Notification();
                bean.setId(rs.getInt("id"));
                bean.setOrderId(rs.getInt("order_id"));
                bean.setStaffId(rs.getInt("staff_id"));
                bean.setDate(rs.getDate("date"));
                bean.setContent(rs.getString("content"));
                bean.setState(Notification.State.valueOf(rs.getString("state")));
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
