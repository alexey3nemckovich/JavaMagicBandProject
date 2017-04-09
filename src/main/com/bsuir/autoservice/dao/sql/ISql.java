package main.com.bsuir.autoservice.dao.sql;

import main.com.bsuir.autoservice.bean.Bean;

import java.util.List;
import java.util.Map;

public interface ISql {
    //queries
    String getUpdateQuery(Bean bean);
    String getDeleteQuery(Bean bean);
    String getInsertQuery(String tableName, List<String> values);
    String getInsertQuery(String tableName, Map<String, String> values);
    String getScopedStatement(List<String> elements, boolean quotedValues);
    //statements
    String getValuesStatement(List<String> values);
    String getConditionStatement(Map<String, String> values);
}
