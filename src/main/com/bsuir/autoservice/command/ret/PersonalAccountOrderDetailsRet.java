package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.impl.Service;

import java.util.List;
import java.util.Objects;

public class PersonalAccountOrderDetailsRet {
    public  final List<Service> orderServices;

    public PersonalAccountOrderDetailsRet(List<Service> orderServices) {
        this.orderServices = orderServices;
    }

    public List<Service> getOrderServices() {
        return orderServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountOrderDetailsRet that = (PersonalAccountOrderDetailsRet) o;
        return Objects.equals(orderServices, that.orderServices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderServices);
    }
}
