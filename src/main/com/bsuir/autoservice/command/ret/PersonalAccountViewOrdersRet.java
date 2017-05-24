package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.impl.Order;

import java.util.List;
import java.util.Objects;

public class PersonalAccountViewOrdersRet {
    public  final List<Order> userOrders;

    public PersonalAccountViewOrdersRet(List<Order> userOrders) {
        this.userOrders = userOrders;
    }

    public List<Order> getUserOrders() {
        return userOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountViewOrdersRet that = (PersonalAccountViewOrdersRet) o;
        return Objects.equals(userOrders, that.userOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userOrders);
    }
}
