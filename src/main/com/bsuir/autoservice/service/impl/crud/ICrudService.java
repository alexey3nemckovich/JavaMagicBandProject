package main.com.bsuir.autoservice.service.impl.crud;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ICrudService extends IService {
    int readTotalCount(String showTableName) throws ServiceException;
    //dependency
    <Entity extends Bean> List<Dependency> readDependencies(String showTableName, Entity entity) throws ServiceException;
    //crud
    <Entity extends Bean> List<Entity> read(String showTableName, Map<String, String> conditions) throws ServiceException;
    <Entity extends Bean> List<Entity> read(String showTableName, int index, int count) throws ServiceException;
    <Entity extends Bean> boolean create(String showTableName, Entity entity) throws ServiceException;
    <Entity extends Bean> boolean update(String showTableName, Entity entity, Map<String, String> conditionValues) throws ServiceException;
    <Entity extends Bean> boolean delete(String showTableName, Entity entity) throws ServiceException;
}
