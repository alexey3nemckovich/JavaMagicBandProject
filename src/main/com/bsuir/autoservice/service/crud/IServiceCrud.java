package main.com.bsuir.autoservice.service.crud;

import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IServiceCrud<PrimaryKey, Entity> extends IService {
    int readTotalCount() throws ServiceException;
    List<Entity> read(int index, int count) throws ServiceException;
    boolean create(Entity entity) throws ServiceException;
    boolean update(Entity entity, Map<String, String> conditionValues) throws ServiceException;
    boolean delete(Entity entity) throws ServiceException;
    String getTableName() throws ServiceException;
}
