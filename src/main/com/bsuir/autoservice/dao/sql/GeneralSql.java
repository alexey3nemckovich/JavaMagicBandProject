package main.com.bsuir.autoservice.dao.sql;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.DatabaseName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GeneralSql implements IGeneralSql {

    private final String databaseName;

    @Inject
    public GeneralSql(@DatabaseName String databaseName){
        this.databaseName = databaseName;
    }

    @Override
    public String getSetForeignKeyChecksStatement(int mode){
        return "SET FOREIGN_KEY_CHECKS=" + String.valueOf(mode);
    }

    private String getFullTableName(String tableName){
        return String.format("`%s`.`%s`", databaseName, tableName);
    }

    @Override
    public String getSelectAllQuery(String tableName){
        return " SELECT * FROM " + getFullTableName(tableName);
    }

    @Override
    public String getSelectWhereStatement(String tableName, Map<String, String> conditions){
        return getSelectAllQuery(tableName) + getWhereStatement(conditions);
    }

    @Override
    public String getSelectWhereStatement(String tableName, Map<String, String> namedResults, Map<String, String> conditions){
        return getSelectNamedQuery(tableName, namedResults) + getWhereStatement(conditions);
    }

    private String getSelectNamedQuery(String tableName, Map<String, String> namedResults){

        return String.format("SELECT %s FROM %s", getNamedStatement(namedResults), getFullTableName(tableName));
    }


    private static final String START_NAMED_STATEMENT = "`%s` as `%s`";
    private static final String OTHER_NAMED_STATEMENT = String.format(", %s", START_NAMED_STATEMENT);

    private String getNamedStatement(Map<String, String> namedResults) {
        StringBuilder stringBuilder = new StringBuilder();
        String currentStatement = START_NAMED_STATEMENT;
        for (Map.Entry<String, String> entry : namedResults.entrySet()) {
            stringBuilder.append(String.format(currentStatement, entry.getKey(), entry.getValue()));
            currentStatement = OTHER_NAMED_STATEMENT;
        }
        return stringBuilder.toString();
    }

    @Override
    public String getSelectCountQuery(String tableName, String varName){
        return " SELECT COUNT(*) AS " +
                varName +
                " FROM " + getFullTableName(tableName);
    }

    @Override
    public String getSelectRangeQuery(String tableName, int startIndex, int count){
        return getSelectAllQuery(getFullTableName(tableName)) +
                " LIMIT " +
                startIndex + ", " + count;
    }

    @Override
    public String getDeleteQuery(String tableName, Map<String, String> values){
        return " DELETE FROM " +
                getFullTableName(tableName) +
                getWhereStatement(values);
    }

    @Override
    public String getInsertQuery(String tableName, List<String> values){
        return "INSERT INTO " +
                getFullTableName(tableName) +
                getValuesStatement(values);
    }

    @Override
    public String getInsertQuery(String tableName, Map<String, String> values){
        return " INSERT INTO " +
                getFullTableName(tableName) +
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
                getFullTableName(tableName) +
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


    private static final String START_WHERE_STATEMENT = "`%s` = '%s'";
    private static final String OTHER_WHERE_STATEMENT = String.format(" AND %s", START_WHERE_STATEMENT);

    @Override
    public String getWhereStatement(Map<String, String> elements){
        StringBuilder stringBuilder = new StringBuilder(" WHERE ");
        String currentStatement = START_WHERE_STATEMENT;
        for (Map.Entry<String, String> array: elements.entrySet()){
            stringBuilder.append(String.format(currentStatement, array.getKey(), array.getValue()));
            currentStatement = OTHER_WHERE_STATEMENT;
        }
        return stringBuilder.toString();
    }

    @Override
    public String getScopedStatement(List<String> elements, boolean quotedValues){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" (");
        for(int i = 0; i < elements.size(); i++){
            if(quotedValues){
                if(reservedWords.contains(elements.get(i))){
                    stringBuilder.append(elements.get(i));
                }else{
                    stringBuilder.append("'" + elements.get(i) + "'");
                }
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

    private static final List<String> reservedWords = new ArrayList<>(
            Arrays.asList(
                    "NULL"
            )
    );
}
