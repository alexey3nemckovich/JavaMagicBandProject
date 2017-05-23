package main.com.bsuir.autoservice.library.type.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleDate extends Date {

    public SimpleDate(String dateStr) throws ParseException {
        if (dateStr != null) {
            date = dateFormat.parse(dateStr);
        }
    }

    public SimpleDate() {
        date = new Date();
    }

    @Override
    public String toString() {
        return date == null ? "---" : dateFormat.format(date);
    }

    private Date date;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
}
