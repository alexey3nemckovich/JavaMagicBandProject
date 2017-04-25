package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;
import main.com.bsuir.autoservice.command.RequestParameter;

import java.util.Map;

public class LoginInfo implements ICommandParam{
    @RequestParameter
    private String login;
    @RequestParameter
    private String password;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
