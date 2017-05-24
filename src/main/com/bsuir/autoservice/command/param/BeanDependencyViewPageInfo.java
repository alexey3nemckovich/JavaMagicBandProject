package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.RequestParameter;
import main.com.bsuir.autoservice.service.Dependency;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class BeanDependencyViewPageInfo extends BeanViewPageInfo {

    @RequestParameter
    public Dependency dependency;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params)
        throws ParseException{
        try {
            Map<String, String[]> mParams = new HashMap<>(
                    super.parse(params, false)
            );

            dependency = Dependency.fromUrlEncodedJson(mParams.get("dependency")[0]);
            mParams.remove("dependency");

            for (Map.Entry<String, String[]> param: mParams.entrySet()){
                fields.put(param.getKey(), param.getValue()[0]);
            }
            return mParams;
        }catch (Exception e){
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
