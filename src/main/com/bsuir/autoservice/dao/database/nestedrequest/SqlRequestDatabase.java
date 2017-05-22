package main.com.bsuir.autoservice.dao.database.nestedrequest;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.infrastructure.listener.DatabaseConnectionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RequestScoped
public class SqlRequestDatabase implements IDatabase {

    private final SqlDatabase sqlDatabase;
    private final DatabaseConnectionListener databaseConnectionListener;
    // use method getConnection, not field directly
    private Connection requestConnection;

    @Inject
    public SqlRequestDatabase(SqlDatabase sqlDatabase,
                              DatabaseConnectionListener databaseConnectionListener){
        this.sqlDatabase = sqlDatabase;
        this.databaseConnectionListener = databaseConnectionListener;
        requestConnection = null;
    }

    public Connection getConnection() throws SQLException {
        requestConnection = requestConnection == null
                ? getConnectionNotify()
                : requestConnection;
        return requestConnection;
    }

    private Connection getConnectionNotify() throws SQLException {
        Connection connection = sqlDatabase.getConnection();
        databaseConnectionListener.notifyGetConnection();
        return connection;
    }

    public void returnConnection(Connection connection) throws SQLException {
        assert connection != null : "Connection is null";
        sqlDatabase.returnConnection(connection);
        requestConnection = requestConnection.equals(connection)
                ? null
                : requestConnection;
    }

    @Override
    public List<String> getListTableNames() throws SQLException {
        return sqlDatabase.getListTableNames(getConnection());
    }

    @Override
    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return sqlDatabase.getPrepareStatement(getConnection(), sql);
    }

    @Override
    public Statement createStatement() throws SQLException {
        return sqlDatabase.createStatement(getConnection());
    }
}
