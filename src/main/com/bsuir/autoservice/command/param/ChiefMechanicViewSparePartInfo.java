package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class ChiefMechanicViewSparePartInfo implements ICommandParam{
    private int pageElementCount;
    private int pageNumber;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public int getPageElementCount() {
        return pageElementCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
