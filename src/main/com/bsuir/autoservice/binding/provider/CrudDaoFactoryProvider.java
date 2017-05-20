package main.com.bsuir.autoservice.binding.provider;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;
import main.com.bsuir.autoservice.dao.impl.crudfactory.ICrudDaoFactory;

import java.util.Map;

public class CrudDaoFactoryProvider implements Provider<ICrudDaoFactory> {

    private final ICrudDaoFactory crudDaoFactory;

    @Inject
    public CrudDaoFactoryProvider(Injector injector, @Named(value = "crudDaoFactory") ICrudDaoFactory crudDaoFactory,
                                  IDatabaseMap databaseMap) {
        this.crudDaoFactory = crudDaoFactory;

        updateFactory(injector, databaseMap);
    }

    private void updateFactory(Injector injector, IDatabaseMap databaseMap){
        try {
            for (Map.Entry<String, Class<? extends ICrudDao>> entryTableNameCrudDao : databaseMap.getShowTableNameDaoCrud().entrySet()) {
                crudDaoFactory.addCrud(entryTableNameCrudDao.getKey(), injector.getInstance(entryTableNameCrudDao.getValue()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ICrudDaoFactory get() {
        return crudDaoFactory;
    }
}
