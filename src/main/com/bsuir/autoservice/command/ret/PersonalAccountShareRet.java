package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.share;

import java.util.List;
import java.util.Objects;

public class PersonalAccountShareRet {
    private final List<share> activeAccountShares;

    public PersonalAccountShareRet(List<share> activeAccountShares) {
        this.activeAccountShares = activeAccountShares;
    }

    public List<share> getActiveAccountShares() {
        return activeAccountShares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o ==null || getClass() != o.getClass()) return false;
        PersonalAccountShareRet that = (PersonalAccountShareRet) o;
        return Objects.equals(activeAccountShares, that.activeAccountShares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activeAccountShares);
    }
}
