package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.RequestParameter;
import main.com.bsuir.autoservice.library.json.JsonParser;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class BeanEditPageInfo extends EditFormPageInfo {

    @RequestParameter
    public Map<String, String> oldFields;

    public BeanEditPageInfo(){
        super();
        oldFields = new HashMap<>();
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

            if(!action.equals("get")){
                oldFields = new HashMap<>(
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
