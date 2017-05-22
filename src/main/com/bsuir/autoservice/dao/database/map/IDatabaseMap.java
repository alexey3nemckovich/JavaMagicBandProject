package main.com.bsuir.autoservice.dao.database.map;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.dao.database.map.beanhelper.TableMap;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;

import java.util.List;
import java.util.Map;

public interface IDatabaseMap {
    List<String> getShowTableNames();
    TableMap getTableMap(Class<? extends ICrudDao> crudDaoClass);
    Map<String, Class<? extends ICrudDao>> getShowTableNameDaoCrud();
    <P> Bean<P> getBeanInstance(String showTableName, Map<String, String> fields) throws BeanException;
    <P> Bean<P> getBeanInstance(String showTableName) throws BeanException;
    String getDatabaseName();
}
