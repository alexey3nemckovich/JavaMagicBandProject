package main.com.bsuir.autoservice.command.ret;

import java.util.Objects;

public class ChiefMechanicChangeSparePartRet {
    private final boolean isUpdateSparePart;

    public ChiefMechanicChangeSparePartRet(boolean isUpdateSparePart) {
        this.isUpdateSparePart = isUpdateSparePart;
    }

    public boolean isUpdateSparePart() {
        return isUpdateSparePart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiefMechanicChangeSparePartRet that = (ChiefMechanicChangeSparePartRet) o;
        return isUpdateSparePart == that.isUpdateSparePart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isUpdateSparePart);
    }
}
