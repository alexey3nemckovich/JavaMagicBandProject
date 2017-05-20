package main.com.bsuir.autoservice.dao.impl.crudfactory;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;

public interface ICrudDaoFactory {
    <PrimaryKey, Entity extends Bean> void addCrud(String tableName, ICrudDao<PrimaryKey, Entity> crudDao) throws DaoException;
    void removeCrud(String tableName) throws DaoException;
    <PrimaryKey, Entity extends Bean> ICrudDao getCrud(String tableName) throws DaoException;
}
