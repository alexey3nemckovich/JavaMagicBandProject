package main.com.bsuir.autoservice.service.impl;

import main.com.bsuir.autoservice.bean.impl.share;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IShareService extends IServiceCrud<Integer, share> {
    List<share> getActiveShares() throws ServiceException;
    List<share> getActiveAccountShares(Integer userId) throws ServiceException;
}
