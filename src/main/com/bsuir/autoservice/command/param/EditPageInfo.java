package main.com.bsuir.autoservice.command.param;

import jdk.nashorn.internal.parser.JSONParser;
import main.com.bsuir.autoservice.command.ICommandParam;
import org.json.JSONObject;

import javax.inject.Inject;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class EditPageInfo extends CrudPageInfo implements ICommandParam{

    public LinkedHashMap<String, String> oldFields;

    @Inject
    EditPageInfo(){
        oldFields = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params){
        try {
            LinkedHashMap<String, String[]> mParams = new LinkedHashMap<>(super.parse(params));
            String oldValues = mParams.get("oldValues")[0].toString();
            oldValues = oldValues.replaceAll("\\\\", "");
            JSONObject jsonBeanOldValues = new JSONObject(oldValues);
            Map<String, Object> oldFields = jsonBeanOldValues.toMap();
            for (Map.Entry<String, Object> entry : oldFields.entrySet()) {
                if(entry.getValue() instanceof String){
                    this.oldFields.put(entry.getKey(), entry.getValue().toString());
                }
            }
            mParams.remove("oldValues");
            fields.clear();
            for (Map.Entry<String, String[]> param: mParams.entrySet()) {
                fields.put(param.getKey(), param.getValue()[0]);
            }
            return mParams;
        }catch (Exception e){
            return null;
        }
    }
}
