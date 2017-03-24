package main.com.bsuir.autoservice.dao.database.impl.sql.impl;

import main.com.bsuir.autoservice.dao.database.exception.DatabaseException;
import main.com.bsuir.autoservice.dao.database.impl.sql.ISqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDatabase implements ISqlDatabase {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public Connection getConnection() throws DatabaseException {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/auto_service_shop?useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root",
                    "root"
            );
        }catch (SQLException e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public void returnConnection(Connection connection) throws DatabaseException {

    }
}
