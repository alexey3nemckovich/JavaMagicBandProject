package main.com.bsuir.notepads.command.param;

import main.com.bsuir.notepads.command.ICommandParam;
import main.com.bsuir.notepads.command.RequestParameter;
import main.com.bsuir.notepads.library.json.JsonParser;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeanEditPageInfo extends EditFormPageInfo implements ICommandParam{

    @RequestParameter
    public LinkedHashMap<String, String> oldFields;

    @Inject
    public BeanEditPageInfo(){
        super();
        oldFields = new LinkedHashMap<>();
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

            if(!action.equals("get")){
                oldFields = new LinkedHashMap<>(
                        JsonParser.parseJsonMap(mParams.get("oldValues")[0].toString())
                );
                mParams.remove("oldValues");
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
