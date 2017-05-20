package main.com.bsuir.autoservice.service.impl.notifiaction;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.impl.Notification;
import main.com.bsuir.autoservice.dao.unitofwork.IDaoUnitOfWork;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;


public class NotificationService implements INotificationService {

    @Inject
    public NotificationService(IDaoUnitOfWork daoUnitOfWork){
        this.daoUnitOfWork = daoUnitOfWork;
    }

    private final IDaoUnitOfWork daoUnitOfWork;

    @Override
    public boolean haveNewNotification() throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Notification> getOrderNotifications(int userId, int orderId, int notificationGroup, int notificationNumber) throws ServiceException {
        throw new UnsupportedOperationException();
    }
}
