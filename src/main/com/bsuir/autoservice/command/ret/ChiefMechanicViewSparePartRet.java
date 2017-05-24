package main.com.bsuir.autoservice.command.ret;

import main.com.bsuir.autoservice.bean.impl.SparePart;

import java.util.List;
import java.util.Objects;

public class ChiefMechanicViewSparePartRet {
    public  final List<SparePart> groupSpareParts;

    public ChiefMechanicViewSparePartRet(List<SparePart> groupSpareParts) {
        this.groupSpareParts = groupSpareParts;
    }

    public List<SparePart> getGroupSpareParts() {
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
