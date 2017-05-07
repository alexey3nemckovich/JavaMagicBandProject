package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeanDependencyViewPageInfo extends BeanViewPageInfo implements ICommandParam{
    @RequestParameter
    public String dependencyTableName;

    public String dependencyFieldName;
    public Object dependencyFieldValue;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params)
        throws ParseException{
        LinkedHashMap<String, String[]> mParams = new LinkedHashMap<String, String[]>(
                super.parse(params, false)
        );

        dependencyTableName = (mParams.get("dependencyTableName")[0]);
        mParams.remove("dependencyTableName");

        if(!action.equals("get")){
            dependencyFieldName = (mParams.get("dependencyFieldName"))[0];
            mParams.remove("dependencyFieldName");

            dependencyFieldValue = (mParams.get("dependencyFieldValue")[0]);
            mParams.remove("dependencyFieldValue");
        }

        for (Map.Entry<String, String[]> param: mParams.entrySet()){
            fields.put(param.getKey(), param.getValue()[0]);
        }
        return mParams;
    }
}
