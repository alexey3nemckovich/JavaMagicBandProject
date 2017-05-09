package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.bean.spare_part;
import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class ChiefMechanicChangeSparePartInfo implements ICommandParam{
    private spare_part sparePart;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public spare_part getSparePart() {
        return sparePart;
    }
}
