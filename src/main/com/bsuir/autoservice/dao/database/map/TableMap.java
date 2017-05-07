package main.com.bsuir.autoservice.dao.database.map;

import java.util.List;
import java.util.Objects;

public class TableMap {
    private final Class domainClass;
    private final String tableName;
    private final String showName;
    private final List<ColumnMap> columnMaps;

    public TableMap(Class domainClass, String tableName, String showName, List<ColumnMap> columnMaps) {
        this.domainClass = domainClass;
        this.tableName = tableName;
        this.showName = showName;
        this.columnMaps = columnMaps;
    }

    public Class getDomainClass() {
        return domainClass;
    }

    public String getTableName() {
        return tableName;
    }

    public String getShowName() {
        return showName;
    }

    public List getColumnMaps() {
        return columnMaps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableMap tableMap = (TableMap) o;
        return Objects.equals(domainClass, tableMap.domainClass) &&
                Objects.equals(tableName, tableMap.tableName) &&
                Objects.equals(showName, tableMap.showName) &&
                Objects.equals(columnMaps, tableMap.columnMaps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainClass, tableName, showName, columnMaps);
    }
}
