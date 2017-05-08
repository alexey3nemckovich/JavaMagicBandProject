package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.util.Map;

public class PersonalAccountViewOrdersInfo implements ICommandParam{
    private int currentPage;
    private int pageElementCount;

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) {
        throw new UnsupportedOperationException();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageElementCount() {
        return pageElementCount;
    }
}
