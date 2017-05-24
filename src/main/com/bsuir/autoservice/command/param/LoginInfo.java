package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.RequestParameter;

public class LoginInfo extends BaseParseCommandParam{
    @RequestParameter
    private String login;
    @RequestParameter
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
