package main.com.bsuir.autoservice.dao.impl;

import main.com.bsuir.autoservice.dao.IDao;
import main.com.bsuir.autoservice.dao.database.map.beanhelper.DependencyMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface ICrudDao<PrimaryKey, Entity> extends IDao{
    List<Entity> read(Map<String, String> conditions) throws DaoException;
    List<Entity> read(int startRange, int count) throws DaoException;
    List<Entity> readAll() throws DaoException;
    boolean update(Entity entity, Map<String, String> conditionValues) throws DaoException;
    boolean delete(Entity entity) throws DaoException;
    boolean insert(Entity entity) throws DaoException;
    List<DependencyMap> getDependencyMaps() throws DaoException;
}
