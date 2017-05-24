package main.com.bsuir.autoservice.dao.database.nestedrequest;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.nestedrequest.connection.ISqlConnection;
import main.com.bsuir.autoservice.library.function.CheckedConsumer;
import main.com.bsuir.autoservice.library.function.CheckedFunction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDatabase implements IDatabase {
    private final ISqlConnection sqlConnections;

    @Inject
    public SqlDatabase(ISqlConnection sqlConnections){
        this.sqlConnections = sqlConnections;
    }

    Connection getConnection() throws SQLException {
        return sqlConnections.getConnection();
    }

    void returnConnection(Connection connection) throws SQLException {
        sqlConnections.returnConnection(connection);
    }

    @Override
    public Statement createStatement() throws SQLException {
        return doInteriorFunction(this::createStatement);
    }

    Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public List<String> getListTableNames() throws SQLException{
        return doInteriorFunction(this::getListTableNames);
    }

    List<String> getListTableNames(Connection connection) throws SQLException {
        String[] types = {"TABLE"};
        DatabaseMetaData md = connection.getMetaData();
        ResultSet tables = md.getTables(connection.getCatalog(), null, "%", types);
        List<String> tableNames = new ArrayList<>();
        while (tables.next()) {
            tableNames.add(tables.getString(3));
        }
        return tableNames;
    }

    protected final <T> T doInteriorFunction(CheckedFunction<Connection, T, SQLException> functionWithConnection)
            throws SQLException {
        Connection connection = getInteriorConnection();
        try {
            return functionWithConnection.apply(connection);
        }finally {
            returnInteriorConnection(connection);
        }
    }

    protected final void doInteriorConsumer(CheckedConsumer<Connection, SQLException> consumerWithConnection)
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
        return doInteriorFunction(connection-> getPrepareStatement(connection, sql));
    }

    PreparedStatement getPrepareStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    private Connection getInteriorConnection() throws SQLException {
        return getConnection();
    }

    private void returnInteriorConnection(Connection connection) throws SQLException {
        returnConnection(connection);
    }
}
