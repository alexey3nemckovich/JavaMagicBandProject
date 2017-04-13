package main.com.bsuir.autoservice.dao.sql;

import main.com.bsuir.autoservice.bean.Bean;

import java.util.List;
import java.util.Map;

public interface ISql {
    //select queries
    String getSelectAllQuery(String tableName);
    String getSelectCountQuery(String tableName, String varName);
    String getSelectRangeQuery(String tableName, int startIndex, int count);
    //crud
    String getDeleteQuery(String tableName, Map<String, String> values);
    String getInsertQuery(String tableName, List<String> values);
    String getInsertQuery(String tableName, Map<String, String> values);
    String getUpdateQuery(String tableName, Map<String, String> conditionValues, Map<String, String> newValues);
    //statements
    String getValuesStatement(List<String> values);
    String getSetStatement(Map<String, String> values);
    String getWhereStatement(Map<String, String> values);
    String getScopedStatement(List<String> elements, boolean quotedValues);
}
