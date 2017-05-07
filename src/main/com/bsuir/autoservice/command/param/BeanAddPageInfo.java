package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeanAddPageInfo extends EditFormPageInfo implements ICommandParam{

    @RequestParameter
    public LinkedHashMap<String, String> defaultValues;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params)
        throws ParseException{
        return parse(params, true);
    }

    @Override
    protected Map<String, String[]> parse(Map<String, String[]> params, boolean passRemainderToFieldsMap)
        throws ParseException{
        LinkedHashMap<String, String[]> mParams = new LinkedHashMap<>(
                super.parse(params, false)
        );

        if(mParams.containsKey("defaultValues")){
            defaultValues = parseJsonMap(mParams.get("defaultValues")[0].toString());
            mParams.remove("defaultValues");
        }

        if(passRemainderToFieldsMap){
            for (Map.Entry<String, String[]> param: mParams.entrySet()) {
                fields.put(param.getKey(), param.getValue()[0]);
            }
        }
        return mParams;
    }
}
