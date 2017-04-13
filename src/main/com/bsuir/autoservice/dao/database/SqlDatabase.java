package main.com.bsuir.autoservice.dao.database;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;

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
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/auto_service_shop?useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "root"
        );
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
