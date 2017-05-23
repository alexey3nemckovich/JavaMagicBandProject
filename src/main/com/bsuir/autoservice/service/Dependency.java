package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.library.json.JsonParser;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Dependency<V> {

    public Dependency(String tableName, String fieldName, V value){
        this.tableName = tableName;
        this.fieldName = fieldName;
        this.value = value;
    }

    public Map<String, String> getCondition(){
        Map<String, String> condition = new HashMap<>();
        condition.put(fieldName, value.toString());
        return condition;
    }

    public String getTableName(){
        return tableName;
    }

    public String getFieldName(){
        return fieldName;
    }

    public V getValue(){
        return value;
    }

    public String getUrlEncodedJson()
            throws UnsupportedEncodingException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tableName", tableName);
        jsonObject.put("fieldName", fieldName);
        jsonObject.put("value", value);
        return URLEncoder.encode(jsonObject.toString(), "UTF-8");
    }

    public static Dependency fromUrlEncodedJson(String jsonString)
            throws UnsupportedEncodingException{
        JSONObject jsonObject = JsonParser.parseUrlEncodedJsonString(jsonString);
        return new Dependency(
                jsonObject.getString("tableName"),
                jsonObject.getString("fieldName"),
                jsonObject.get("value")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependency<?> that = (Dependency<?>) o;
        return Objects.equals(tableName, that.tableName) &&
                Objects.equals(fieldName, that.fieldName) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, fieldName, value);
    }

    public String tableName;
    public String fieldName;
    public V value;
}
