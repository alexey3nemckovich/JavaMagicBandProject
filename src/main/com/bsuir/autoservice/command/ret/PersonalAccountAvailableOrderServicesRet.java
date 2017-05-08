package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.bean.share;

import java.util.List;
import java.util.Objects;

public class PersonalAccountAvailableOrderServicesRet {
    private final List<service> availableServices;
    private final List<share> activeAccountShares;

    public PersonalAccountAvailableOrderServicesRet(List<service> availableServices, List<share> activeAccountShares) {
        this.availableServices = availableServices;
        this.activeAccountShares = activeAccountShares;
    }

    public List<service> getAvailableServices() {
        return availableServices;
    }

    public List<share> getActiveAccountShares() {
        return activeAccountShares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null | getClass() != o.getClass()) return false;
        PersonalAccountAvailableOrderServicesRet that = (PersonalAccountAvailableOrderServicesRet) o;
        return Objects.equals(availableServices, that.availableServices) &&
                Objects.equals(activeAccountShares, that.activeAccountShares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableServices, activeAccountShares);
    }
}
