package main.com.bsuir.autoservice.infrastructure.listener;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.RequestScoped;
import main.com.bsuir.autoservice.binding.annotation.InjectLogger;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.SqlRequestDatabase;
import org.apache.log4j.Logger;

@RequestScoped
public class DatabaseConnectionListener implements AutoCloseable{
    private final Provider<SqlRequestDatabase> requestDatabaseProvider;
    private boolean isGetConnection;
    @InjectLogger
    Logger logger;

    @Inject
    public DatabaseConnectionListener(Provider<SqlRequestDatabase> requestDatabaseProvider){
        this.requestDatabaseProvider = requestDatabaseProvider;
        isGetConnection = false;
    }

    public void notifyGetConnection(){
        isGetConnection = true;
    }

    public void closeIfOpenConnection() {
        IDatabase requestDatabase = requestDatabaseProvider.get();
        try{
        if (isGetConnection){
            requestDatabase.returnConnection(requestDatabase.getConnection());
        }}catch (Exception e){
            logger.error("Not a close listener", e);
        }
    }

    @Override
    public void close(){
        closeIfOpenConnection();
    }
}
