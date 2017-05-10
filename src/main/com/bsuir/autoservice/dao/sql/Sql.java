package main.com.bsuir.autoservice.dao.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sql implements ISql {

    @Override
    public String getSetForeignKeyChecksStatement(int mode){
        return "SET FOREIGN_KEY_CHECKS=" + String.valueOf(mode);
    }

    @Override
    public String getSelectAllQuery(String tableName){
        return " SELECT * FROM " + tableName;
    }

    @Override
    public String getSelectWhereStatement(String tableName, Map<String, String> conditions){
        return getSelectAllQuery(tableName) + getWhereStatement(conditions);
    }

    @Override
    public String getSelectCountQuery(String tableName, String varName){
        return " SELECT COUNT(*) AS " +
                varName +
                " FROM " + tableName;
    }

    @Override
    public String getSelectRangeQuery(String tableName, int startIndex, int count){
        return getSelectAllQuery(tableName) +
                " LIMIT " +
                startIndex + ", " + count;
    }

    @Override
    public String getDeleteQuery(String tableName, Map<String, String> values){
        return " DELETE FROM " +
                tableName +
                getWhereStatement(values);
    }

    @Override
    public String getInsertQuery(String tableName, List<String> values){
        return "INSERT INTO " +
                tableName +
                getValuesStatement(values);
    }

    @Override
    public String getInsertQuery(String tableName, Map<String, String> values){
        return " INSERT INTO " +
                tableName +
                getScopedStatement(
                        new ArrayList<String>(values.keySet()), false
                ) + " " +
                getValuesStatement(
                        new ArrayList<String>(values.values())
                );
    }

    @Override
    public String getUpdateQuery(String tableName, Map<String, String> conditionValues, Map<String, String> newValues){
        return " UPDATE " +
                tableName +
                getSetStatement(newValues) +
                getWhereStatement(conditionValues);
    }

    @Override
    public String getSetStatement(Map<String, String> values){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" SET ");
        Map.Entry[] array = values.entrySet().toArray(new Map.Entry[0]);
        for(int i = 0; i < values.size(); i++){
            stringBuilder.append(
                    array[i].getKey() + "=" + "'" + array[i].getValue() + "' "
            );
            if(values.size() - 1 != i){
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String getValuesStatement(List<String> values) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" VALUES ");
        stringBuilder.append(getScopedStatement(values, true));
        return stringBuilder.toString();
    }

    @Override
    public String getWhereStatement(Map<String, String> elements){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" WHERE ");
        Map.Entry[] array = elements.entrySet().toArray(new Map.Entry[0]);
        for(int i = 0; i < elements.size(); i++){
            stringBuilder.append(
                    array[i].getKey() + "=" + "'" + array[i].getValue() + "' "
            );
            if(elements.size() - 1 != i){
                stringBuilder.append(" and ");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String getScopedStatement(List<String> elements, boolean quotedValues){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" (");
        for(int i = 0; i < elements.size(); i++){
            if(quotedValues){
                stringBuilder.append("'" + elements.get(i) + "'");
            }else{
                stringBuilder.append(elements.get(i));
            }
            if(elements.size() - 1 != i){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(") ");
        return stringBuilder.toString();
    }
}
