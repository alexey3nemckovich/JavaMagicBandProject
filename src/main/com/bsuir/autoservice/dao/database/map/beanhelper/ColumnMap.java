package main.com.bsuir.autoservice.dao.database.map.beanhelper;

import java.util.Objects;

public class ColumnMap {
    private final String databaseField;
    private final String applicationField;

    public ColumnMap(String databaseField, String applicationField) {
        this.databaseField = databaseField;
        this.applicationField = applicationField;
    }

    public String getDatabaseField() {
        return databaseField;
    }

    public String getApplicationField() {
        return applicationField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnMap columnMap = (ColumnMap) o;
        return Objects.equals(databaseField, columnMap.databaseField) &&
                Objects.equals(applicationField, columnMap.applicationField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(databaseField, applicationField);
    }
}
