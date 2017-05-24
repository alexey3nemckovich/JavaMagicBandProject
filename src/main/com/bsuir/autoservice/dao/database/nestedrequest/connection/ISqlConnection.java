package main.com.bsuir.autoservice.dao.database.nestedrequest.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISqlConnection {
    Connection getConnection() throws SQLException;
    void returnConnection(Connection connection) throws SQLException;
}
