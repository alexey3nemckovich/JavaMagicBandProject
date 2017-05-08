package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.library.json.JsonParser;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Dependency {

    public Dependency(String tableName, String name, Object value){
        this.tableName = tableName;
        this.name = name;
        this.value = value;
    }

    public Map<String, String> getCondition(){
        Map<String, String> condition = new HashMap<>();
        condition.put(name, value.toString());
        return condition;
    }

    public String getTableName(){
        return tableName;
    }

    public String getName(){
        return name;
    }

    public Object getValue(){
        return value;
    }

    public String getUrlEncodedJson()
            throws UnsupportedEncodingException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tableName", tableName);
        jsonObject.put("name", name);
        jsonObject.put("value", value);
        return URLEncoder.encode(jsonObject.toString(), "UTF-8");
    }

    public static Dependency fromUrlEncodedJson(String jsonString)
            throws UnsupportedEncodingException{
        JSONObject jsonObject = JsonParser.parseUrlEncodedJsonString(jsonString);
        return new Dependency(
                jsonObject.getString("tableName"),
                jsonObject.getString("name"),
                jsonObject.get("value")
        );
    }

    public String tableName;
    public String name;
    public Object value;
}
