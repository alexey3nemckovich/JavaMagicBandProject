package main.com.bsuir.autoservice.command;

import java.util.Map;

public interface ICommandParam {
    //returns map of not parsed parameters
    Map<String, String[]> parse(Map<String, String[]> params);
}
