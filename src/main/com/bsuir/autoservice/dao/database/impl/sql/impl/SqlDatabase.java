package main.com.bsuir.autoservice.dao.database.impl.sql.impl;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;
import main.com.bsuir.autoservice.dao.database.exception.DatabaseException;
import main.com.bsuir.autoservice.dao.database.impl.sql.ISqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDatabase implements ISqlDatabase {
    private final String url;
    private final String login;
    private final String password;

    @Inject
    public SqlDatabase(ISqlConfigDatabase sqlConfigDatabase){
        try {
            Class.forName(sqlConfigDatabase.getDriverProvider());
            url = sqlConfigDatabase.getUrl();
            login =sqlConfigDatabase.getLogin();
            password =sqlConfigDatabase.getPassword();
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
