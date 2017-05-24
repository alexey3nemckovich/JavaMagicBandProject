package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.impl.Share;

import java.util.List;
import java.util.Objects;

public class PersonalAccountShareRet {
    public  final List<Share> activeAccountShares;

    public PersonalAccountShareRet(List<Share> activeAccountShares) {
        this.activeAccountShares = activeAccountShares;
    }

    public List<Share> getActiveAccountShares() {
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
