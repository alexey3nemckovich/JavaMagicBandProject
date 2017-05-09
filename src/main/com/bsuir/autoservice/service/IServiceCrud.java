package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IServiceCrud<PrimaryKey, Entity> extends IService {
    int readTotalCount() throws ServiceException;
    List<Entity> read(int index, int count) throws ServiceException;
    boolean create(Entity entity) throws ServiceException;
    boolean update(Entity entity, Map<String, String> conditionValues) throws ServiceException;
    boolean delete(Entity entity) throws ServiceException;
}
