package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

public interface INotificationService extends IService {
    boolean haveNewNotification() throws ServiceException;
}
