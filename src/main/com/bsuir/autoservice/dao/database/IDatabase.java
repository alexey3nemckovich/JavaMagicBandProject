package main.com.bsuir.autoservice.dao.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface IDatabase {
    List<String> getListTableNames() throws SQLException;
    PreparedStatement getPrepareStatement(String sql) throws SQLException;
    String getName();
    Statement createStatement() throws SQLException;
}
