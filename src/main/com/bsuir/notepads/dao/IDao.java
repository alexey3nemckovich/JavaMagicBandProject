package main.com.bsuir.notepads.dao;

import main.com.bsuir.notepads.dao.exception.DaoException;

public interface IDao {
    String getTableName();
    String getFullTableName();
    int getCountRecords() throws DaoException;
}
