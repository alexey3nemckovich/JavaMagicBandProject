package main.com.bsuir.autoservice.dao;

import main.com.bsuir.autoservice.dao.exception.DaoException;

public interface IDao {
    int getCountRecords() throws DaoException;
}
