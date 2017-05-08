package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.bean.share;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;

public interface IShareService extends IService {
    List<share> getActiveShares() throws ServiceException;
    List<share> getActiveAccountShares(Integer userId) throws ServiceException;
}
