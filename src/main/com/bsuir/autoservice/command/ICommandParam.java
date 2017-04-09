package main.com.bsuir.autoservice.command;

import java.util.Map;

public interface ICommandParam {
    ICommandParam parse(Map<String, String[]> params);
}
