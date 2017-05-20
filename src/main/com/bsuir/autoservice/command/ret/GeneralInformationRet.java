package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.service;
import main.com.bsuir.autoservice.bean.share;

import java.util.List;
import java.util.Objects;

public class GeneralInformationRet {
    private final List<service> availableServices;

    private final List<share> activeShares;

    public GeneralInformationRet(List<service> availableServices, List<share> activeShares) {
        this.availableServices = availableServices;
        this.activeShares = activeShares;
    }

    public List<service> getAvailableServices() {
        return availableServices;
    }

    public List<share> getActiveShares() {
        return activeShares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralInformationRet that = (GeneralInformationRet) o;
        return Objects.equals(availableServices, that.availableServices) &&
                Objects.equals(activeShares, that.activeShares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableServices, activeShares);
    }
}
