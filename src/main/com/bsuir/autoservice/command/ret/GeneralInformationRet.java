package main.com.bsuir.autoservice.command.ret;


import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.bean.impl.Share;

import java.util.List;
import java.util.Objects;

public class GeneralInformationRet {
    private final List<Service> availableServices;

    private final List<Share> activeShares;

    public GeneralInformationRet(List<Service> availableServices, List<Share> activeShares) {
        this.availableServices = availableServices;
        this.activeShares = activeShares;
    }

    public List<Service> getAvailableServices() {
        return availableServices;
    }

    public List<Share> getActiveShares() {
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
