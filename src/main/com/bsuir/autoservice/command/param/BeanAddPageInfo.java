package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;
import main.com.bsuir.autoservice.library.json.JsonParser;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeanAddPageInfo extends EditFormPageInfo implements ICommandParam{

    @RequestParameter
    public Map<String, String> defaultValues;

    public BeanAddPageInfo(){
        defaultValues = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params)
        throws ParseException{
        return parse(params, true);
    }

    @Override
    protected Map<String, String[]> parse(Map<String, String[]> params, boolean passRemainderToFieldsMap)
        throws ParseException{
        try {
            Map<String, String[]> mParams = new HashMap<>(
                    super.parse(params, false)
            );

            if(mParams.containsKey("defaultValues")){
                defaultValues = new LinkedHashMap<>(
                        JsonParser.parseJsonMap(mParams.get("defaultValues")[0].toString())
                );
                mParams.remove("defaultValues");
            }

            if(passRemainderToFieldsMap){
                for (Map.Entry<String, String[]> param: mParams.entrySet()) {
                    fields.put(param.getKey(), param.getValue()[0]);
                }
            }
            return mParams;
        }catch (Exception e){
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
