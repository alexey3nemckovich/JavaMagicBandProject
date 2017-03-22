package main.com.bsuir.autoservice.service.impl.crud;

import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public interface IServiceCrud<PrimaryKey, Entity> extends IService {
    boolean create(List<Entity> createEntities) throws ServiceException;
    List<Entity> read(int startRange, int count) throws ServiceException;
    boolean update(List<Entity> updageEntities) throws ServiceException;
    boolean delete(List<PrimaryKey> deleteKeys) throws ServiceException;
    String getTableName() throws ServiceException;
}
