package main.com.bsuir.autoservice.command;

import java.util.Map;

public interface ICommandParam {
    Map<String, String[]> parse(Map<String, String[]> params);
    Map<String, String[]> parse(Map<String, String[]> params, boolean passRemainderToMap);
}
