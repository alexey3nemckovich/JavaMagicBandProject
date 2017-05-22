package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class LogoutRet {
    private final boolean isLogout;

    public LogoutRet(boolean isLogout) {
        this.isLogout = isLogout;
    }

    public boolean isLogout() {
        return isLogout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogoutRet logoutRet = (LogoutRet) o;
        return isLogout == logoutRet.isLogout;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLogout);
    }
}
