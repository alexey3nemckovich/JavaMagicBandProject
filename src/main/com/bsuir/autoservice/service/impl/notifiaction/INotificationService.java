package main.com.bsuir.autoservice.service.impl;

import main.com.bsuir.autoservice.bean.impl.notification;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface INotificationService extends IServiceCrud<Integer, notification> {
    boolean haveNewNotification() throws ServiceException;
    List<notification> getOrderNotifications(int userId, int orderId, int notificationGroup, int notificationNumber)
            throws ServiceException;
}
