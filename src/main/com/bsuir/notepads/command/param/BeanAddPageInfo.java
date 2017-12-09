package main.com.bsuir.notepads.command.param;

import com.google.inject.Inject;
import main.com.bsuir.notepads.command.ICommandParam;
import main.com.bsuir.notepads.command.RequestParameter;
import main.com.bsuir.notepads.library.json.JsonParser;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeanAddPageInfo extends EditFormPageInfo implements ICommandParam{

    @RequestParameter
    public LinkedHashMap<String, String> defaultValues;

    @Inject
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
            LinkedHashMap<String, String[]> mParams = new LinkedHashMap<>(
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
