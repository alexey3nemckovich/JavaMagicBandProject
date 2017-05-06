package main.com.bsuir.autoservice.dao.database;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class SqlRequestDatabaseProvider implements IDatabase{
    private final Provider<SqlRequestDatabase> requestDatabaseProvider;

    @Inject
    public SqlRequestDatabaseProvider(Provider<SqlRequestDatabase> requestDatabaseProvider){
        this.requestDatabaseProvider = requestDatabaseProvider;
    }

    private IDatabase getDatabase(){
        return requestDatabaseProvider.get();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getDatabase().getConnection();
    }

    @Override
    public void returnConnection(Connection connection) throws SQLException {
        getDatabase().returnConnection(connection);
    }

    @Override
    public List<String> getListTableNames() throws SQLException {
        return getDatabase().getListTableNames();
    }

    @Override
    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return getDatabase().getPrepareStatement(sql);
    }
}
