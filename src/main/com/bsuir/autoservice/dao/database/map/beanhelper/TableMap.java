package main.com.bsuir.autoservice.dao.database.map.beanhelper;

import java.util.List;
import java.util.Objects;

public class TableMap {
    private final Class domainClass;
    private final String tableName;
    private final List<ColumnMap> columnMaps;
    private final List<DependencyMap> dependencyMaps;

    public TableMap(Class domainClass, String tableName, List<ColumnMap> columnMaps, List<DependencyMap> dependencyMaps) {
        this.domainClass = domainClass;
        this.tableName = tableName;
        this.columnMaps = columnMaps;
        this.dependencyMaps = dependencyMaps;
    }

    public Class getDomainClass() {
        return domainClass;
    }

    public String getTableName() {
        return tableName;
    }

    public List getColumnMaps() {
        return columnMaps;
    }

    public List<DependencyMap> getDependencyMaps() {
        return dependencyMaps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableMap tableMap = (TableMap) o;
        return Objects.equals(domainClass, tableMap.domainClass) &&
                Objects.equals(tableName, tableMap.tableName) &&
                Objects.equals(columnMaps, tableMap.columnMaps) &&
                Objects.equals(dependencyMaps, tableMap.dependencyMaps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainClass, tableName, columnMaps, dependencyMaps);
    }
}
