package main.com.bsuir.autoservice.dao.sql;

import java.util.List;
import java.util.Map;

public interface IGeneralSql {
    //modes queries
    String getSetForeignKeyChecksStatement(int mode);
    //select queries
    String getSelectAllQuery(String tableName);
    String getSelectNamedQuery(String tableName, Map<String, String> namedResult);
    String getSelectCountQuery(String tableName, String varName);
    String getSelectRangeQuery(String tableName, int startIndex, int count);
    String getSelectWhereRangeQuery(String tableName, Map<String, String> conditions, int startIndex, int count);
    String getSelectWhereStatement(String tableName, Map<String, String> conditions);
    String getSelectWhereStatement(String tableName, Map<String, String> namedResults, Map<String, String> conditions);
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

    //exist
    String getExistsStatement(String tableName, Map<String, String> conditions, String namedExist);

    String getLastPrimaryKeyStatement(String namedKey);
    String getSelectWhereInStatement(String tableName, String inVariable, List<String> conditions);
}
