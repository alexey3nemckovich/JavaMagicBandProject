package main.com.bsuir.autoservice.dao;

import main.com.bsuir.autoservice.dao.exception.DaoException;

import java.util.List;

public interface DaoController<Entity, PrimaryKey> extends IDao{
    List<Entity> getAll() throws DaoException;
    List<Entity> getRange(int startRange, int count) throws DaoException;
    Entity getByPrimaryKey(PrimaryKey key) throws DaoException;
    boolean update(List<Entity> updateEntities) throws DaoException;
    boolean delete(List<PrimaryKey> deleteKeys) throws DaoException;
    boolean insert(List<Entity> insertEntities) throws DaoException;
    String getTableName() throws DaoException;
}