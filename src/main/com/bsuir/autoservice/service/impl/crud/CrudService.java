package main.com.bsuir.autoservice.service.impl.crud;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.dao.IDao;
import main.com.bsuir.autoservice.dao.database.map.beanhelper.DependencyMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;
import main.com.bsuir.autoservice.dao.impl.crudfactory.ICrudDaoFactory;
import main.com.bsuir.autoservice.library.function.CheckedFunction;
import main.com.bsuir.autoservice.service.Dependency;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrudService implements ICrudService {

    @Inject
    public CrudService(ICrudDaoFactory crudDaoFactory){
        this.crudDaoFactory = crudDaoFactory;
    }

    @Override
    public <Entity extends Bean> List<Dependency> readDependencies(String showTableName, Entity entity) throws ServiceException {
        return checkedCompleteOperation(showTableName, iCrudDao ->
            new ArrayList<Dependency>(){{
                for(DependencyMap dependencyMap: (List<DependencyMap>)iCrudDao.getDependencyMaps()){
                    add(new Dependency<>(dependencyMap.getShowTableName(), dependencyMap.getFieldName(), entity.getId()));
                }
            }});
    }

    @Override
    public <Entity extends Bean> List<Entity> read(String showTableName, Map<String, String> conditions) throws ServiceException {
        return checkedCompleteOperation(showTableName, iCrudDao -> iCrudDao.read(conditions));
    }

    @Override
    public <Entity extends Bean> List<Entity> read(String showTableName, int index, int count) throws ServiceException {
        return checkedCompleteOperation(showTableName, iCrudDao -> iCrudDao.read(index, count));
    }

    @Override
    public <Entity extends Bean> boolean create(String showTableName, Entity entity) throws ServiceException {
        return checkedCompleteOperation(showTableName, iCrudDao -> iCrudDao.insert(entity));
    }

    @Override
    public <Entity extends Bean> boolean update(String showTableName, Entity entity, Map<String, String> conditionValues) throws ServiceException {
        return checkedCompleteOperation(showTableName, iCrudDao -> iCrudDao.update(entity, conditionValues));
    }

    @Override
    public <Entity extends Bean> boolean delete(String showTableName, Entity entity) throws ServiceException {
        return checkedCompleteOperation(showTableName, iCrudDao -> iCrudDao.delete(entity));
    }

    private <R> R checkedCompleteOperation(String tableName, CheckedFunction<ICrudDao, R, DaoException> checkedFunction)
            throws ServiceException{
        try{
            return checkedFunction.apply(getCrudDao(tableName));
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    private <PrimaryKey, Entity extends Bean> ICrudDao<PrimaryKey, Entity> getCrudDao(String tableName) throws DaoException {
        return crudDaoFactory.getCrud(tableName);
    }

    @Override
    public int readTotalCount(String showTableName) throws ServiceException{
        return checkedCompleteOperation(showTableName, IDao::getCountRecords);
    }

    private final ICrudDaoFactory crudDaoFactory;
}
