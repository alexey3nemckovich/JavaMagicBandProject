package main.com.bsuir.autoservice.dao.impl.notification;

import main.com.bsuir.autoservice.bean.impl.Notification;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;

public interface INotificationDao extends ICrudDao<Integer, Notification> {
    boolean haveNewNotification() throws DaoException;
}
