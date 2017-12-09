package main.com.bsuir.notepads.command.param;

import main.com.bsuir.notepads.command.ICommandParam;
import main.com.bsuir.notepads.command.RequestParameter;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CrudPageInfo implements ICommandParam{
    @RequestParameter
    public String tableName;
    @RequestParameter
    public String action;

    public LinkedHashMap<String, String> fields;
    public String result;

    @Inject
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
        LinkedHashMap<String, String[]> mParams = new LinkedHashMap<>(params);

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
