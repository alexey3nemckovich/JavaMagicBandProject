package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.impl.Notification;

import java.util.List;
import java.util.Objects;

public class PersonalAccountOrderNotificationsRet {
    public  final List<Notification> orderNotifications;

    public PersonalAccountOrderNotificationsRet(List<Notification> orderNotifications) {
        this.orderNotifications = orderNotifications;
    }

    public List<Notification> getOrderNotifications() {
        return orderNotifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountOrderNotificationsRet that = (PersonalAccountOrderNotificationsRet) o;
        return Objects.equals(orderNotifications, that.orderNotifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNotifications);
    }
}
