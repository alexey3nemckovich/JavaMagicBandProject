package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.text.ParseException;
import java.util.Map;

public class NoInfo implements ICommandParam{
    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) throws ParseException {
        return null;
    }
}
