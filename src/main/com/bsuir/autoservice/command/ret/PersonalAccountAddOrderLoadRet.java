package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.impl.Service;
import main.com.bsuir.autoservice.bean.impl.ServiceShop;

import java.util.List;
import java.util.Objects;

public class PersonalAccountAddOrderLoadRet {
    public  final List<Service> fullAvailableServices;
    public  final List<ServiceShop> fullWorkingShop;

    public PersonalAccountAddOrderLoadRet(List<Service> fullAvailableServices, List<ServiceShop> fullWorkingShop) {
        this.fullAvailableServices = fullAvailableServices;
        this.fullWorkingShop = fullWorkingShop;
    }

    public List<Service> getFullAvailableServices() {
        return fullAvailableServices;
    }

    public List<ServiceShop> getFullWorkingShop() {
        return fullWorkingShop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountAddOrderLoadRet that = (PersonalAccountAddOrderLoadRet) o;
        return Objects.equals(fullAvailableServices, that.fullAvailableServices) &&
                Objects.equals(fullWorkingShop, that.fullWorkingShop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullAvailableServices, fullWorkingShop);
    }
}
