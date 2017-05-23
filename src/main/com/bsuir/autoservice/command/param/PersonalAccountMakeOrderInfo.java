package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PersonalAccountMakeOrderInfo implements ICommandParam{
    private List<Integer> orderServices;
    private Integer serviceShopId;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        orderServices = new ArrayList<Integer>(){{
           String[] services = params.get("orderService");
           if (services != null){
               for (String serviceId : services){
                   add(Integer.valueOf(serviceId));
               }
           }
        }};

        String shopId = params.get("orderServiceShop")[0];
        serviceShopId = shopId == null ? null : Integer.valueOf(shopId);

        return null;
    }

    public List<Integer> getOrderServices() {
        return orderServices;
    }

    public Integer getServiceShopId() {
        return serviceShopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountMakeOrderInfo that = (PersonalAccountMakeOrderInfo) o;
        return Objects.equals(orderServices, that.orderServices) &&
                Objects.equals(serviceShopId, that.serviceShopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderServices, serviceShopId);
    }
}
