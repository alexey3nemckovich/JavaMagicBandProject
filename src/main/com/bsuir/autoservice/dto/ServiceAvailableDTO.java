package main.com.bsuir.autoservice.dto;

import java.util.Objects;

public class ServiceAvailableDTO {
    private final String nameService;
    private final int costService;

    public ServiceAvailableDTO(String nameService, int costService) {
        this.nameService = nameService;
        this.costService = costService;
    }

    public String getNameService() {
        return nameService;
    }

    public int getCostService() {
        return costService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceAvailableDTO that = (ServiceAvailableDTO) o;
        return costService == that.costService &&
                Objects.equals(nameService, that.nameService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameService, costService);
    }
}
