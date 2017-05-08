package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;
import main.com.bsuir.autoservice.service.Dependency;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BeanViewPageInfo extends CrudPageInfo implements ICommandParam{
    @RequestParameter
    public int page;
    @RequestParameter
    public int countRecords;

    public List<? extends Bean> beans;
    public Map<Bean, List<Dependency>> dependencyMap;
    public int totalPagesCount;

    public BeanViewPageInfo(){
        super();
        beans = new ArrayList<>();
        dependencyMap = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params)
        throws ParseException{
        return parse(params, true);
    }

    @Override
    protected Map<String, String[]> parse(Map<String, String[]> params, boolean passRemainderToFieldsMap)
        throws ParseException{
        LinkedHashMap<String, String[]> mParams = new LinkedHashMap<String, String[]>(
                super.parse(params, false)
        );

        if(!mParams.containsKey("page")){
            page = 1;
        }else{
            page = Integer.valueOf(mParams.get("page")[0]);
            mParams.remove("page");
            if(0 == page){
                page = 1;
            }
        }

        if(!mParams.containsKey("countRecords")){
            countRecords = 3;
        }else {
            countRecords = Integer.valueOf(mParams.get("countRecords")[0]);
            mParams.remove("countRecords");
            if(0 == countRecords){
                countRecords = 3;
            }
        }

        if(passRemainderToFieldsMap){
            for (Map.Entry<String, String[]> param: mParams.entrySet()) {
                fields.put(param.getKey(), param.getValue()[0]);
            }
        }
        return mParams;
    }
}
