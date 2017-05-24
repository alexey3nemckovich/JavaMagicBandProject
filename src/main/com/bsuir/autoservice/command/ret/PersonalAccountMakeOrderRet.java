package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class PersonalAccountMakeOrderRet {
    public  final boolean isAddedOrder;

    public PersonalAccountMakeOrderRet(boolean isAddedOrder) {
        this.isAddedOrder = isAddedOrder;
    }

    public boolean isAddedOrder() {
        return isAddedOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountMakeOrderRet that = (PersonalAccountMakeOrderRet) o;
        return isAddedOrder == that.isAddedOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAddedOrder);
    }
}
