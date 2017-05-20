package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.impl.User;
import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class PersonalAccountUpdateGeneralInformationInfo implements ICommandParam{
    private User newUser;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public User getNewUser() {
        return newUser;
    }
}
