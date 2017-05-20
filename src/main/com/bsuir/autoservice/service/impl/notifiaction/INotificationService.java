package main.com.bsuir.autoservice.service.impl.notifiaction;

import main.com.bsuir.autoservice.bean.impl.Notification;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface INotificationService extends IService {
    boolean haveNewNotification() throws ServiceException;
    List<Notification> getOrderNotifications(int userId, int orderId, int notificationGroup, int notificationNumber)
            throws ServiceException;
}
