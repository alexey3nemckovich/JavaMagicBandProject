package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.impl.Share;
import main.com.bsuir.autoservice.dto.ServiceAvailableDTO;

import java.util.List;
import java.util.Objects;

public class PersonalAccountAvailableOrderServicesRet {
    public  final List<ServiceAvailableDTO> availableServices;
    public  final List<Share> activeAccountShares;

    public PersonalAccountAvailableOrderServicesRet(List<ServiceAvailableDTO> availableServices, List<Share> activeAccountShares) {
        this.availableServices = availableServices;
        this.activeAccountShares = activeAccountShares;
    }

    public List<ServiceAvailableDTO> getAvailableServices() {
        return availableServices;
    }

    public List<Share> getActiveAccountShares() {
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
