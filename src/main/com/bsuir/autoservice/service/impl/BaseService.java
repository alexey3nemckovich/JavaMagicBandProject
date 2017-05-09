package main.com.bsuir.autoservice.service.impl;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.Cached;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public class BaseService implements IBaseService {

    @Inject
    public BaseService(IDatabaseMap databaseMap){
        this.databaseMap = databaseMap;
    }

    @Cached
    public List<String> getListTableNames() throws ServiceException{
        try {
            return databaseMap.getShowTableNames();
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    private final IDatabaseMap databaseMap;
}
