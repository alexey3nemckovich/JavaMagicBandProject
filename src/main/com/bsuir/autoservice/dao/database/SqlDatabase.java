package main.com.bsuir.autoservice.dao.database;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;
import main.com.bsuir.autoservice.library.function.CheckedConsumer;
import main.com.bsuir.autoservice.library.function.CheckedFunction;

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
            login = sqlConfigDatabase.getLogin();
            password = sqlConfigDatabase.getPassword();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }

    public void returnConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public List<String> getListTableNames() throws SQLException{
        return doIneriorFunction(this::getListTableNames);
    }

    public List<String> getListTableNames(Connection connection) throws SQLException {
        String[] types = {"TABLE"};
        DatabaseMetaData md = connection.getMetaData();
        ResultSet tables = md.getTables(connection.getCatalog(), null, "%", types);
        List<String> tableNames = new ArrayList<>();
        while (tables.next()) {
            tableNames.add(tables.getString(3));
        }
        return tableNames;
    }

    protected final <T> T doIneriorFunction(CheckedFunction<Connection, T, SQLException> functionWithConnection)
            throws SQLException {
        Connection connection = getInteriorConnection();
        try {
            return functionWithConnection.apply(connection);
        }finally {
            returnInteriorConnection(connection);
        }
    }

    protected final void doInheriorConsumer(CheckedConsumer<Connection, SQLException> consumerWithConnection)
            throws SQLException {
        Connection connection = getInteriorConnection();
        try {
            consumerWithConnection.accept(connection);
        }finally {
            returnInteriorConnection(connection);
        }
    }

    @Override
    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return doIneriorFunction(connection-> getPrepareStatement(connection, sql));
    }

    public PreparedStatement getPrepareStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    private Connection getInteriorConnection() throws SQLException {
        return getConnection();
    }

    private void returnInteriorConnection(Connection connection) throws SQLException {
        returnConnection(connection);
    }
}
