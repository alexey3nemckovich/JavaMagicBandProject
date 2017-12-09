package main.com.bsuir.notepads.dao.database;

import java.sql.*;
import java.util.List;

public interface IDatabase {
    Connection getConnection() throws SQLException;
    void returnConnection(Connection connection) throws SQLException;
    List<String> getListTableNames() throws SQLException;
    String getName();
}
