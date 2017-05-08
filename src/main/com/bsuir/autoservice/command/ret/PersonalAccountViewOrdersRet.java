package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.order;

import java.util.List;
import java.util.Objects;

public class PersonalAccountViewOrdersRet {
    private final List<order> userOrders;

    public PersonalAccountViewOrdersRet(List<order> userOrders) {
        this.userOrders = userOrders;
    }

    public List<order> getUserOrders() {
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
