package main.com.bsuir.autoservice.dao;

import main.com.bsuir.autoservice.dao.exception.DaoException;

import java.util.List;

public interface IDaoCrud<Entity, PrimaryKey> {
    int getAllCount() throws DaoException;
    List<Entity> getRange(int startRange, int count) throws DaoException;
    Entity getByPrimaryKey(PrimaryKey key) throws DaoException;
    int update(List<Entity> updateEntities) throws DaoException;
    int delete(List<PrimaryKey> deleteKeys) throws DaoException;
    int insert(List<Entity> insertEntities) throws DaoException;
    String getTableName() throws DaoException;
}
