package main.com.bsuir.autoservice.dao.database.map;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.BeanException;
import main.com.bsuir.autoservice.service.crud.IServiceCrud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDatabaseMap {
    List<String> getShowTableNames();
    TableMap getTableMap(String showTableName);
    Map<String, Class<? extends IServiceCrud>> getShowTableNameServiceCrud();
    Class<? extends Bean> getBeanClass(String showTableName);
    Bean getBeanInstance(String showTableName, HashMap<String, String> fields)
            throws BeanException;
}
