package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.spare_part;

import java.util.List;
import java.util.Objects;

public class ChiefMechanicViewSparePartRet {
    private final List<spare_part> groupSpareParts;

    public ChiefMechanicViewSparePartRet(List<spare_part> groupSpareParts) {
        this.groupSpareParts = groupSpareParts;
    }

    public List<spare_part> getGroupSpareParts() {
        return groupSpareParts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiefMechanicViewSparePartRet that = (ChiefMechanicViewSparePartRet) o;
        return Objects.equals(groupSpareParts, that.groupSpareParts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupSpareParts);
    }
}
