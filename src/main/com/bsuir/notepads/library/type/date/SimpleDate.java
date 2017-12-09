package main.com.bsuir.notepads.library.type.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDate extends Date {

    public SimpleDate(String dateStr) throws ParseException{
        date = dateFormat.parse(dateStr);
    }

    @Override
    public String toString(){
        return dateFormat.format(date);
    }

    private Date date;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
