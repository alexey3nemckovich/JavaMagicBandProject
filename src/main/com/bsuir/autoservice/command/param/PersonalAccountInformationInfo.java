package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.user;
import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class PersonalAccountInformationInfo implements ICommandParam{
    private boolean authorized;
    private user.Type accountLevel;
    private int userId;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public user.Type getAccountLevel() {
        return accountLevel;
    }

    public int getUserId() {
        return userId;
    }
}
