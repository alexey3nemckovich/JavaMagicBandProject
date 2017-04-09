package main.com.bsuir.autoservice.config.database.impl.sql.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.command.RequestParameter;
import main.com.bsuir.autoservice.config.database.impl.sql.ISqlConfigDatabase;
import main.com.bsuir.autoservice.config.exception.ConfigException;

import java.util.ResourceBundle;

public class SqlConfigDatabase implements ISqlConfigDatabase {
    private final ResourceBundle sqlConfig;

    @Inject
    public SqlConfigDatabase(@Named("sqlConfig") String configSqlResources) throws ConfigException{
        try {
            sqlConfig = ResourceBundle.getBundle(configSqlResources);
        }catch (Exception e){
            throw new ConfigException(e);
        }
    }

    @Override
    public String getDriverProvider() {
        return sqlConfig.getString("driverProvider");
    }

    @Override
    public String getUrl() {
        return sqlConfig.getString("url");
    }

    @Override
    public String getLogin() {
        return sqlConfig.getString("login");
    }

    @Override
    public String getPassword() {
        return sqlConfig.getString("password");
    }
}
