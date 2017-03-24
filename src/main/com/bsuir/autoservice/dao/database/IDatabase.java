package main.com.bsuir.autoservice.dao.database;

import main.com.bsuir.autoservice.dao.database.exception.DatabaseException;

public interface IDatabase<ConnectionType> {
    ConnectionType getConnection() throws DatabaseException;
    void returnConnection(ConnectionType connection) throws DatabaseException;
}
