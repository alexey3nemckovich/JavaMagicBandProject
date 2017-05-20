package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.order;

import java.util.List;
import java.util.Objects;

public class MechanicViewOrdersRet {
    private final List<order> serviceShopOrder;

    public MechanicViewOrdersRet(List<order> serviceShopOrders) {
        this.serviceShopOrder = serviceShopOrders;
    }

    public List<order> getServiceShopOrder() {
        return serviceShopOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MechanicViewOrdersRet that = (MechanicViewOrdersRet) o;
        return Objects.equals(serviceShopOrder, that.serviceShopOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceShopOrder);
    }
}
