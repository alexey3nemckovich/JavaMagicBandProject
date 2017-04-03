package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IServiceCrud<PrimaryKey, Entity>{
    int readTotalCount() throws ServiceException;
    List<Entity> read(int page, int count) throws ServiceException;
    int create(List<Entity> createEntities) throws ServiceException;
    int update(List<Entity> updageEntities) throws ServiceException;
    int delete(List<PrimaryKey> deleteKeys) throws ServiceException;
    String getTableName() throws ServiceException;
}
