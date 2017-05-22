package main.com.bsuir.autoservice.binding.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;

public class DatabaseNameProvider implements Provider<String>{

    private final String databaseName;

    @Inject
    public DatabaseNameProvider(IDatabaseMap databaseMap) {
        this.databaseName = databaseMap.getDatabaseName();
    }

    @Override
    public String get() {
        return databaseName;
    }
}
