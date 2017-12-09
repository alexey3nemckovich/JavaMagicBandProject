package main.com.bsuir.notepads.service;

import main.com.bsuir.notepads.service.crud.exception.ServiceException;

import java.util.List;

public interface IService {
    List<String> getListTableNames() throws ServiceException;
}
