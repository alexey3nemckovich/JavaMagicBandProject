package main.com.bsuir.autoservice.service.impl.service;

import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IServiceBeanService extends IService {
    List<Service> getAvailableServices() throws ServiceException;
}
