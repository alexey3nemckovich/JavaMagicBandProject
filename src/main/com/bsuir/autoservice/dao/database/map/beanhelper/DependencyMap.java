package main.com.bsuir.autoservice.dao.database.map.beanhelper;

import java.util.Objects;

public class DependencyMap {
    private String showTableName;
    private String fieldName;

    public DependencyMap(String showTableName, String fieldName) {
        this.showTableName = showTableName;
        this.fieldName = fieldName;
    }

    public String getShowTableName() {
        return showTableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DependencyMap that = (DependencyMap) o;
        return Objects.equals(showTableName, that.showTableName) &&
                Objects.equals(fieldName, that.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showTableName, fieldName);
    }
}
