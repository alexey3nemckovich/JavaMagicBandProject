package main.com.bsuir.autoservice.command.param;

import main.com.bsuir.autoservice.command.ICommandParam;

import java.text.ParseException;
import java.util.Map;
import java.util.Objects;

public class PersonalAccountViewOrdersInfo implements ICommandParam {
    private int currentPage;
    private int itemsOnPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public int getItemsOnPage() {
        return itemsOnPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccountViewOrdersInfo that = (PersonalAccountViewOrdersInfo) o;
        return currentPage == that.currentPage &&
                itemsOnPage == that.itemsOnPage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPage, itemsOnPage);
    }

    @Override
    public Map<String, String[]> parse(Map<String, String[]> params) throws ParseException {
        currentPage = Integer.valueOf(params.get("currentPage")[0]);
        itemsOnPage = Integer.valueOf(params.get("itemsOnPage")[0]);
        return null;
    }
}
