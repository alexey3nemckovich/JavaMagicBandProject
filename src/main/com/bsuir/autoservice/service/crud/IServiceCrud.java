package main.com.bsuir.autoservice.service.crud;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IServiceCrud<PrimaryKey, Entity extends Bean>{
    String getTableName();
    int readTotalCount() throws ServiceException;
    //dependency
    List<Dependency> readDependencies(Entity entity) throws ServiceException;
    //crud
    List<Entity> read(Map<String, String> conditions) throws ServiceException;
    List<Entity> read(int index, int count) throws ServiceException;
    boolean create(Entity entity) throws ServiceException;
    boolean update(Entity entity, Map<String, String> conditionValues) throws ServiceException;
    boolean delete(Entity entity) throws ServiceException;
}
