package main.com.bsuir.autoservice.command.ret;


import main.com.bsuir.autoservice.config.permission.PermissionLevel;

import java.util.Objects;

public class PersonalAccountModesRet {
    private final PermissionLevel userLevel;

    public PersonalAccountModesRet(PermissionLevel userLevel) {
        this.userLevel = userLevel;
    }

    public PermissionLevel getUserLevel() {
        return userLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountModesRet that = (PersonalAccountModesRet) o;
        return userLevel == that.userLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userLevel);
    }
}
