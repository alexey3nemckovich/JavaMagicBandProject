package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class MechanicChangeOrderStateRet {
    public  final boolean isChangedOrderState;

    public MechanicChangeOrderStateRet(boolean isChangedOrderState) {
        this.isChangedOrderState = isChangedOrderState;
    }

    public boolean isChangedOrderState() {
        return isChangedOrderState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MechanicChangeOrderStateRet that = (MechanicChangeOrderStateRet) o;
        return isChangedOrderState == that.isChangedOrderState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isChangedOrderState);
    }
}
