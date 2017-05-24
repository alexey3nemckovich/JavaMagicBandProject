package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class PersonalAccountUpdateGeneralInformationRet {
    public  final boolean isUpdateUser;

    public PersonalAccountUpdateGeneralInformationRet(boolean isUpdateUser) {
        this.isUpdateUser = isUpdateUser;
    }

    public boolean isUpdateUser() {
        return isUpdateUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountUpdateGeneralInformationRet that = (PersonalAccountUpdateGeneralInformationRet) o;
        return isUpdateUser == that.isUpdateUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isUpdateUser);
    }
}
