package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;

public interface IServiceService extends IService {
    List<service> getAvailableServices() throws ServiceException;
}
