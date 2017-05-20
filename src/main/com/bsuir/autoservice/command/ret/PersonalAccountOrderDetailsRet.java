package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.impl.Order;

import java.util.List;
import java.util.Objects;

public class PersonalAccountOrderDetailsRet {
    private final List<Order> orderServices;

    public PersonalAccountOrderDetailsRet(List<Order> orderServices) {
        this.orderServices = orderServices;
    }

    public List<Order> getOrderServices() {
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
