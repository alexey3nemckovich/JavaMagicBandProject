package main.com.bsuir.autoservice.dao.database.nestedrequest.connection;

import com.google.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SpringConnection implements ISqlConnection{

    private final DataSource dataSource;

    @Inject
    public SpringConnection(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void returnConnection(Connection connection) throws SQLException{
        connection.close();
    }
}
