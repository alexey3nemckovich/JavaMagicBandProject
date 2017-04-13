package main.com.bsuir.autoservice.service;

import com.google.inject.Inject;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseService implements IService{

    @Inject
    public BaseService(Injector injector){
        db = injector.getInstance(IDatabase.class);
    }

    public List<String> getListTableNames() throws ServiceException{
        try {
            List<String> names = db.getListTableNames();
            return names;
        }catch (SQLException e){
            throw new ServiceException(e);
        }
    }

    private final IDatabase db;
}
