package main.com.bsuir.autoservice.dao;

import main.com.bsuir.autoservice.dao.exception.DaoException;

public interface IDao {
    String getTableName() throws DaoException;
}
