package main.com.bsuir.autoservice.service;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class BaseService implements IBaseService {

    @Inject
    public BaseService(IDatabase database){
        db = database;
    }

    public List<String> getListTableNames() throws ServiceException{
        try {
            return db.getListTableNames();
        }catch (SQLException e){
            throw new ServiceException(e);
        }
    }

    private final IDatabase db;
}
