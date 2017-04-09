package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;

import java.util.List;
import java.util.Map;

public class BeanViewPageInfo implements ICommandParam{
    @RequestParameter
    public String name;
    @RequestParameter
    public int page;
    @RequestParameter
    public int countRecords;

    public int totalPagesCount;
    public List<Bean> beans;

    @Override
    public BeanViewPageInfo parse(Map<String, String[]> params){
        name = params.get("name")[0];
        page = Integer.valueOf(params.get("page")[0]);
        countRecords = Integer.valueOf(params.get("countRecords")[0]);
        return this;
    }
}
