package main.com.bsuir.autoservice.service.impl;

import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IBaseService extends IService {
    List<String> getListTableNames() throws ServiceException;
}
