package main.com.bsuir.notepads.service.crud;

import main.com.bsuir.notepads.bean.Bean;
import main.com.bsuir.notepads.service.crud.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IServiceCrud<PrimaryKey, Entity extends Bean>{
    String getTableName();
    int readTotalCount() throws ServiceException;

    //crud
    List<Entity> read(Map<String, String> conditions) throws ServiceException;
    List<Entity> read(int index, int count) throws ServiceException;
    boolean create(Entity entity) throws ServiceException;
    boolean update(Entity entity, Map<String, String> conditionValues) throws ServiceException;
    boolean delete(Entity entity) throws ServiceException;
}
