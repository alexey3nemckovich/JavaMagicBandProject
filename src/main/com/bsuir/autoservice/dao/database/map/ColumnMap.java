package main.com.bsuir.autoservice.dao.database.map;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ColumnMap {
    private final String databaseField;
    private final String applicationField;
    private final List<DependencyMap> dependencyMaps;


    public ColumnMap(String databaseField, String applicationField) {
        this.databaseField = databaseField;
        this.applicationField = applicationField;
        this.dependencyMaps = Collections.emptyList();
    }

    public String getDatabaseField() {
        return databaseField;
    }

    public String getApplicationField() {
        return applicationField;
    }

    public List<DependencyMap> getDependencyMaps() {
        return dependencyMaps;
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
