package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public class BaseParseCommandParam implements ICommandParam{
    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) throws ParseException {
        Class<? extends BaseParseCommandParam> clazz = getClass();

        int position = 0;
        try {
            for (Map.Entry<String, String[]> param : params.entrySet()) {
                Field field = clazz.getDeclaredField(param.getKey());
                field.setAccessible(true);
                field.set(this, param.getValue()[0]);
                ++position;
            }
            return null;
        }catch (Exception e){
            throw new ParseException(String.format("Error parse params '%s' => '%s'",
                    (new ArrayList<>(params.keySet())).get(position),
                    (new ArrayList<>(params.values())).get(position)[0]), position);
        }
    }
}
