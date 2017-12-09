package main.com.bsuir.notepads.service;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.notepads.dao.database.IDatabase;
import main.com.bsuir.notepads.service.crud.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class BaseService implements IService{

    @Inject
    public BaseService(Injector injector){
        db = injector.getInstance(IDatabase.class);
    }

    public List<String> getListTableNames() throws ServiceException{
        try {
            List<String> allDbTables = db.getListTableNames();
            return allDbTables;
        }catch (SQLException e){
            throw new ServiceException(e);
        }
    }

    private final IDatabase db;
}
