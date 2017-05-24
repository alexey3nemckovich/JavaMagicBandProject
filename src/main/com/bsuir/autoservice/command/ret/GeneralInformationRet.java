package main.com.bsuir.autoservice.command.ret;


import main.com.bsuir.autoservice.dto.ServiceAvailableDTO;
import main.com.bsuir.autoservice.dto.ShareActiveDTO;

import java.util.List;
import java.util.Objects;

public class GeneralInformationRet {
    private final List<ServiceAvailableDTO> availableServices;

    private final List<ShareActiveDTO> activeShares;

    public GeneralInformationRet(List<ServiceAvailableDTO> availableServices, List<ShareActiveDTO> activeShares) {
        this.availableServices = availableServices;
        this.activeShares = activeShares;
    }

    public List<ServiceAvailableDTO> getAvailableServices() {
        return availableServices;
    }

    public List<ShareActiveDTO> getActiveShares() {
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
