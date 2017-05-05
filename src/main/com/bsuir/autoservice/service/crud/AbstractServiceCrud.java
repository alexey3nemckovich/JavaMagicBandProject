package main.com.bsuir.autoservice.service.crud;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.crud.IDaoCrud;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractServiceCrud<PrimaryKey,Entity extends Bean> implements IServiceCrud<PrimaryKey,Entity> {

    protected AbstractServiceCrud(IDaoCrud daoCrud){
        this.daoCrud = daoCrud;
    }

    @Override
    public int readTotalCount() throws ServiceException{
        try {
            return daoCrud.getCountRecords();
        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Entity> read(int startIndex, int count) throws ServiceException {
        try {
            return daoCrud.read(startIndex, count);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(Entity entity) throws ServiceException {
        try {
            return daoCrud.insert(entity);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Entity entity, Map<String, String> conditionValues) throws ServiceException {
        try {
            return daoCrud.update(entity, conditionValues);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Entity entity) throws ServiceException {
        try {
            return daoCrud.delete(entity);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public String getTableName() throws ServiceException {
        try{
            return daoCrud.getTableName();
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    protected Dependency getDependencyForTable(
            IDaoCrud dependencyTableDao,
            String dependencyFieldName,
            Object dependencyFieldValue
    ) throws DaoException{
        Map<String,String> condition = new LinkedHashMap<>();
        condition.put(dependencyFieldName, dependencyFieldValue.toString());
        String tableName = dependencyTableDao.getTableName();
        List<? extends Bean> dependencyBeans = dependencyTableDao.read(condition);
        return new Dependency(dependencyFieldName, dependencyFieldValue, tableName, dependencyBeans);
    }

    private final IDaoCrud<PrimaryKey, Entity> daoCrud;
}
