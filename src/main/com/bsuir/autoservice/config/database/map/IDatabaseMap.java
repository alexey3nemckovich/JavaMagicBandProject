package main.com.bsuir.autoservice.config.database.map;

import java.util.List;

public interface IDatabaseMap {
    List<String> getShowTableNames();
    TableMap getTableMap(String showTableName);
}
