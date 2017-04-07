package main.com.bsuir.autoservice.service.crud;

import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;

public interface IServiceCrud<PrimaryKey, Entity>{
    int readTotalCount() throws ServiceException;
    List<Entity> read(int index, int count) throws ServiceException;
    boolean create(Entity entity) throws ServiceException;
    boolean update(Entity entity) throws ServiceException;
    boolean delete(Entity entity) throws ServiceException;
    String getTableName() throws ServiceException;
}
