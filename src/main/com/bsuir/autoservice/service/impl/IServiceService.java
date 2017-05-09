package main.com.bsuir.autoservice.service.impl;

import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IServiceService extends IService {
    List<service> getAvailableServices() throws ServiceException;
}
