package main.com.bsuir.autoservice.service.impl;

import main.com.bsuir.autoservice.bean.impl.service;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IServiceBeanService extends IServiceCrud<Integer, service> {
    List<service> getAvailableServices() throws ServiceException;
}
