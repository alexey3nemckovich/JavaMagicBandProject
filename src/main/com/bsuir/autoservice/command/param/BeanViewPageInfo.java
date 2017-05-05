package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;
import main.com.bsuir.autoservice.service.Dependency;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BeanViewPageInfo extends CrudPageInfo implements ICommandParam{
    @RequestParameter
    public int page;
    @RequestParameter
    public int countRecords;

    public List<Bean> beans;
    public LinkedHashMap<Bean, List<Dependency>> dependenciesMap;
    public int totalPagesCount;

    public BeanViewPageInfo(){
        super();
        beans = new ArrayList<>();
        dependenciesMap = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params){
        LinkedHashMap<String, String[]> mParams = new LinkedHashMap<String, String[]>(
                super.parse(params, false)
        );

        page = Integer.valueOf(mParams.get("page")[0]);
        mParams.remove("page");

        countRecords = Integer.valueOf(mParams.get("countRecords")[0]);
        mParams.remove("countRecords");

        for (Map.Entry<String, String[]> param: mParams.entrySet()) {
            fields.put(param.getKey(), param.getValue()[0]);
        }
        return mParams;
    }
}
