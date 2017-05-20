package main.com.bsuir.autoservice.dao.database.nestedrequest;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import main.com.bsuir.autoservice.dao.database.IDatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Singleton
public class SqlRequestDatabaseProvider implements IDatabase {
    private final Provider<SqlRequestDatabase> requestDatabaseProvider;

    @Inject
    public SqlRequestDatabaseProvider(Provider<SqlRequestDatabase> requestDatabaseProvider){
        this.requestDatabaseProvider = requestDatabaseProvider;
    }

    private IDatabase getDatabase(){
        return requestDatabaseProvider.get();
    }

    @Override
    public List<String> getListTableNames() throws SQLException {
        return getDatabase().getListTableNames();
    }

    @Override
    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return getDatabase().getPrepareStatement(sql);
    }

    @Override
    public String getName() {
        return getDatabase().getName();
    }

    @Override
    public Statement createStatement() throws SQLException {
        return getDatabase().createStatement();
    }
}
