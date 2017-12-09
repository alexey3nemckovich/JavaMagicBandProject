package main.com.bsuir.notepads.command.param;

import main.com.bsuir.notepads.command.ICommandParam;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeanDependencyViewPageInfo extends BeanViewPageInfo implements ICommandParam{

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params)
        throws ParseException{
        try {
            LinkedHashMap<String, String[]> mParams = new LinkedHashMap<String, String[]>(
                    super.parse(params, false)
            );

            for (Map.Entry<String, String[]> param: mParams.entrySet()){
                fields.put(param.getKey(), param.getValue()[0]);
            }
            return mParams;
        }catch (Exception e){
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
