package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class PersonalAccountViewOrderNumberRet {
    public  final int numberOrder;

    public PersonalAccountViewOrderNumberRet(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountViewOrderNumberRet that = (PersonalAccountViewOrderNumberRet) o;
        return numberOrder == that.numberOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOrder);
    }
}
