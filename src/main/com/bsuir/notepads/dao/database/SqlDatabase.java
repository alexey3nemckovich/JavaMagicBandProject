package main.com.bsuir.notepads.dao.database;

import com.google.inject.Inject;
import main.com.bsuir.notepads.config.database.impl.sql.ISqlConfigDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDatabase implements IDatabase {
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
    public String getName(){
        return "notepads";
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/notepads?useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf-8",
                "root",
                "root"
        );

        return connection;
    }

    @Override
    public void returnConnection(Connection connection) throws SQLException {

    }

    @Override
    public List<String> getListTableNames() throws SQLException{
        Connection connection = getConnection();

        String[] types = {"TABLE"};
        DatabaseMetaData md = connection.getMetaData();
        ResultSet tables = md.getTables(connection.getCatalog(), null, "%", types);
        List<String> tableNames = new ArrayList<>();
        while (tables.next()) {
            tableNames.add(tables.getString(3));
        }
        returnConnection(connection);
        return tableNames;
    }
}
