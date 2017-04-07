package main.com.bsuir.autoservice.dao.sql;

import main.com.bsuir.autoservice.bean.Bean;

import java.util.List;

public class Sql implements ISql {

    public String getUpdateQuery(Bean bean) {
        return null;
    }

    public String getDeleteQuery(Bean bean){
        return null;
    }

    public String getInsertQuery(Bean bean){
        return null;
    }

    public String getInsertQuery(List<Bean> beans){
        return null;
    }
}
