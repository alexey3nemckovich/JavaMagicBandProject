package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class LoginRet {
    private final boolean isAuthorized;

    public LoginRet(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRet loginRet = (LoginRet) o;
        return isAuthorized == loginRet.isAuthorized;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAuthorized);
    }
}
