package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.RequestParameter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditFormPageInfo extends CrudPageInfo{

    @RequestParameter
    public List<String> notModifiableFieldsNames;

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
        Map<String, String[]> mParams = new HashMap<>(
                super.parse(params, false)
        );

        if(mParams.containsKey("notModifiableFieldsNames")){
            setNotModifiableFieldsNames(mParams);
        }

        if(passRemainderToFieldsMap){
            for (Map.Entry<String, String[]> param: mParams.entrySet()) {
                fields.put(param.getKey(), param.getValue()[0]);
            }
        }
        return mParams;
    }

    private void setNotModifiableFieldsNames(Map<String, String[]> mParams){
        for(String string : mParams.get("notModifiableFieldsNames")){
            notModifiableFieldsNames.add(string);
        }
        mParams.remove("notModifiableFieldsNames");
    }
}
