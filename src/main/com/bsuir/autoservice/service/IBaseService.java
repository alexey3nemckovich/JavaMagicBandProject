package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;

public interface IBaseService extends IService {
    List<String> getListTableNames() throws ServiceException;
}
