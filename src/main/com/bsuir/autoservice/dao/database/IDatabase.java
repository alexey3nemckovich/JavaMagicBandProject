package main.com.bsuir.autoservice.dao.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface IDatabase {
    List<String> getListTableNames() throws SQLException;
    PreparedStatement getPrepareStatement(String sql) throws SQLException;
}
