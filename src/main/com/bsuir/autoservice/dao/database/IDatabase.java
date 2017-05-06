package main.com.bsuir.autoservice.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface IDatabase {
    Connection getConnection() throws SQLException;
    void returnConnection(Connection connection) throws SQLException;
    List<String> getListTableNames() throws SQLException;
    PreparedStatement getPrepareStatement(String sql) throws SQLException;
}
