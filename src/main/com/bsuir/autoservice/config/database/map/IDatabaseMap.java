package main.com.bsuir.autoservice.config.database.map;

import main.com.bsuir.autoservice.service.crud.IServiceCrud;

import java.util.List;
import java.util.Map;

public interface IDatabaseMap {
    List<String> getShowTableNames();
    TableMap getTableMap(String showTableName);
    Map<String, Class<? extends IServiceCrud>> getShowTableNameServiceCrud();
}
