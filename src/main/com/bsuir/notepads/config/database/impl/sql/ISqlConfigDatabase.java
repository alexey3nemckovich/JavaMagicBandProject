package main.com.bsuir.notepads.config.database.impl.sql;

import main.com.bsuir.notepads.config.database.IConfigDatabase;

public interface ISqlConfigDatabase extends IConfigDatabase{
    String getDriverProvider();
    String getUrl();
    String getLogin();
    String getPassword();
}
