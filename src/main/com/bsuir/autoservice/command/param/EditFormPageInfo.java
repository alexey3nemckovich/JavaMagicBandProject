package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.RequestParameter;
import org.json.JSONObject;

import javax.inject.Inject;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EditFormPageInfo extends CrudPageInfo{

    @RequestParameter
    public List<String> notModifiableFieldsNames;

    @Inject
    public EditFormPageInfo(){
        notModifiableFieldsNames = new ArrayList<>();
    }

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

        setNotModifiableFieldsNames(mParams);

        if(passRemainderToFieldsMap){
            for (Map.Entry<String, String[]> param: mParams.entrySet()) {
                fields.put(param.getKey(), param.getValue()[0]);
            }
        }
        return mParams;
    }

    protected LinkedHashMap<String, String> parseJsonMap(String jsonMapStr)
            throws ParseException{
        try {
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            jsonMapStr = URLDecoder.decode(jsonMapStr, "UTF-8");
            jsonMapStr = jsonMapStr.replaceAll("\\\\", "");
            JSONObject jsonMap = new JSONObject(jsonMapStr);
            Map<String, Object> oldFields = jsonMap.toMap();
            for (Map.Entry<String, Object> entry : oldFields.entrySet()) {
                if(entry.getValue() instanceof String){
                    map.put(entry.getKey(), entry.getValue().toString());
                }
            }
            return map;
        }catch (Exception e){
            throw new ParseException(e.getMessage(), 0);
        }
    }

    private void setNotModifiableFieldsNames(Map<String, String[]> mParams){
        for(String string : mParams.get("notModifiableFieldsNames")){
            notModifiableFieldsNames.add(string);
        }
        mParams.remove("notModifiableFieldsNames");
    }
}
