package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class MechanicAddOrderNotificationRet {
    private final boolean isAddedOrderNotification;

    public MechanicAddOrderNotificationRet(boolean isAddedOrderNotification) {
        this.isAddedOrderNotification = isAddedOrderNotification;
    }

    public boolean isAddedOrderNotification() {
        return isAddedOrderNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MechanicAddOrderNotificationRet that = (MechanicAddOrderNotificationRet) o;
        return isAddedOrderNotification == that.isAddedOrderNotification;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAddedOrderNotification);
    }
}
