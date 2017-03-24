package main.com.bsuir.autoservice.service.impl.crud;

import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IServiceCrud<PrimaryKey, Entity> extends IService {
    int create(List<Entity> createEntities) throws ServiceException;
    List<Entity> read(int startRange, int count) throws ServiceException;
    int update(List<Entity> updageEntities) throws ServiceException;
    int delete(List<PrimaryKey> deleteKeys) throws ServiceException;
    String getTableName() throws ServiceException;
}
