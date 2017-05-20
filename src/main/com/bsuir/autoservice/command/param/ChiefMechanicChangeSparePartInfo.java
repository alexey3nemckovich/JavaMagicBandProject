package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.impl.SparePart;
import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class ChiefMechanicChangeSparePartInfo implements ICommandParam{
    private SparePart sparePart;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public SparePart getSparePart() {
        return sparePart;
    }
}
