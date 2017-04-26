package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.bean.share;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;

public interface IShareService {
    List<share> getActiveShares() throws ServiceException;
}
