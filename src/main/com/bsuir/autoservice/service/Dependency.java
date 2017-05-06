package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.bean.Bean;

import java.util.List;

public class Dependency {

    public Dependency(String name, Object value, String tableName, List<? extends Bean> beans){
        this.name = name;
        this.value = value;
        this.tableName = tableName;
        this.beans = beans;
    }

    public List<? extends Bean> getBeans(){
        return beans;
    }

    public String name;
    public Object value;
    public String tableName;
    public List<? extends Bean> beans;
}
