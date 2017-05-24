package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class PersonalAccountRestorePassRet {
    public  final boolean isRestoredPass;

    public PersonalAccountRestorePassRet(boolean isRestoredPass) {
        this.isRestoredPass = isRestoredPass;
    }

    public boolean isRestoredPass() {
        return isRestoredPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountRestorePassRet that = (PersonalAccountRestorePassRet) o;
        return isRestoredPass == that.isRestoredPass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isRestoredPass);
    }
}
