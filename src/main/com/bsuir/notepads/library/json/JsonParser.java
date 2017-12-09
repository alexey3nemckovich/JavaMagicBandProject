package main.com.bsuir.notepads.library.json;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonParser{

    public static JSONObject parseUrlEncodedJsonString(String string)
            throws UnsupportedEncodingException{
        string = URLDecoder.decode(string, "UTF-8");
        string = string.replaceAll("\\\\", "");
        return new JSONObject(string);
    }

    public static Map<String, String> parseJsonMap(String jsonMapStr)
            throws ParseException {
        try {
            Map<String, String> map = new LinkedHashMap<>();
            JSONObject jsonObject = JsonParser.parseUrlEncodedJsonString(jsonMapStr);
            Map<String, Object> fields = jsonObject.toMap();
            for (Map.Entry<String, Object> entry : fields.entrySet()) {
                if(entry.getValue() instanceof String){
                    map.put(entry.getKey(), entry.getValue().toString());
                }
            }
            return map;
        }catch (Exception e){
            throw new ParseException(e.getMessage(), 0);
        }
    }
}
