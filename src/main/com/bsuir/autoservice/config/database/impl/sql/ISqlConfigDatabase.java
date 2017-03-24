package main.com.bsuir.autoservice.config.database.impl.sql;

import main.com.bsuir.autoservice.config.database.IConfigDatabase;

public interface ISqlConfigDatabase extends IConfigDatabase{
    String getDriverProvider();
    String getUrl();
    String getLogin();
    String getPassword();
}
