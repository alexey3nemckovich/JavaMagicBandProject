package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;

import java.util.Map;

public class ResetLoginInfo implements ICommandParam{
    @RequestParameter
    private String email;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public String getEmail() {
        return email;
    }
}
