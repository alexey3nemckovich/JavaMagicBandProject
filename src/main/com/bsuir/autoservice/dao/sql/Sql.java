package main.com.bsuir.autoservice.dao.sql;

import main.com.bsuir.autoservice.bean.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sql implements ISql {

    @Override
    public String getUpdateQuery(Bean bean) {
        return null;
    }

    @Override
    public String getDeleteQuery(Bean bean){
        return null;
    }

    @Override
    public String getInsertQuery(String tableName, List<String> values){
        return "INSERT INTO " +
                tableName +
                getValuesStatement(values);
    }

    @Override
    public String getInsertQuery(String tableName, Map<String, String> values){
        return "INSERT INTO " +
                tableName +
                getScopedStatement(
                        new ArrayList<String>(values.keySet()), false
                ) + " " +
                getValuesStatement(
                        new ArrayList<String>(values.values())
                );
    }

    @Override
    public String getValuesStatement(List<String> values) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" VALUES ");
        stringBuilder.append(getScopedStatement(values, true));
        return stringBuilder.toString();
    }

    @Override
    public String getConditionStatement(Map<String, String> values){
        return null;
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
