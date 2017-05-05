package main.com.bsuir.autoservice.dao.crud.notification;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.notification;
import main.com.bsuir.autoservice.dao.crud.AbstractDaoCrud;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class NotificationDao extends AbstractDaoCrud<Integer, notification> implements INotificationDao {

    @Inject
    public NotificationDao(IDatabase db, ISql sql) {
        super(db, sql);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public List<notification> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<notification> result = new LinkedList<>();
        try {
            while (rs.next()) {
                notification bean = new notification();
                result.add(bean);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private final String tableName = "notification";
}
