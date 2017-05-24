package main.com.bsuir.autoservice.dao.database.nestedrequest.connection;


import com.google.inject.Inject;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConfigConnection implements ISqlConnection{
    private final String url;
    private final String login;
    private final String password;

    @Inject
    public SqlConfigConnection(ISqlConfigDatabase sqlConfigDatabase){
        try {
            Class.forName(sqlConfigDatabase.getDriverProvider());
            url = sqlConfigDatabase.getUrl();
            login = sqlConfigDatabase.getLogin();
            password = sqlConfigDatabase.getPassword();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }

    @Override
    public void returnConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
