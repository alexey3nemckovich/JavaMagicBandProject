package main.com.bsuir.notepads.command;

import java.text.ParseException;
import java.util.Map;

public interface ICommandParam {
    //returns map of not parsed parameters
    Map<String, String[]> parse(Map<String, String[]> params) throws ParseException;
}
