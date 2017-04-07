package main.com.bsuir.autoservice.dao.sql;

import main.com.bsuir.autoservice.bean.Bean;

import java.util.List;

public interface ISql {
    String getUpdateQuery(Bean bean);
    String getDeleteQuery(Bean bean);
    String getInsertQuery(Bean bean);
    String getInsertQuery(List<Bean> beans);
}
