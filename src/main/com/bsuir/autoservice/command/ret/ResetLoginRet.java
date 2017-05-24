package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class ResetLoginRet {
    public  final boolean isResetLogin;

    public ResetLoginRet(boolean isResetLogin) {
        this.isResetLogin = isResetLogin;
    }

    public boolean isResetLogin() {
        return isResetLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResetLoginRet that = (ResetLoginRet) o;
        return isResetLogin == that.isResetLogin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isResetLogin);
    }
}
