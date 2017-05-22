package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CrudPageInfo implements ICommandParam{
    @RequestParameter
    public String tableName;
    @RequestParameter
    public String action;

    public Map<String, String> fields;
    public String result;

    public CrudPageInfo(){
        fields = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params)
        throws ParseException{
        return parse(params, true);
    }

    protected Map<String, String[]> parse(Map<String, String[]> params, boolean passRemainderToFieldsMap)
        throws ParseException{
        Map<String, String[]> mParams = new HashMap<>(params);

        tableName = mParams.get("tableName")[0];
        mParams.remove("tableName");

        if(mParams.containsKey("action")) {
            action = mParams.get("action")[0];
            mParams.remove("action");
        }else {
            action = "get";
        }

        if(passRemainderToFieldsMap){
            for (Map.Entry<String, String[]> param: mParams.entrySet()) {
                fields.put(param.getKey(), param.getValue()[0]);
            }
        }
        return mParams;
    }
}
