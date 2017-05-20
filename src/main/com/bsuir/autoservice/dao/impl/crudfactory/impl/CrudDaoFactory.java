package main.com.bsuir.autoservice.dao.impl.crudfactory.impl;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.impl.ICrudDao;
import main.com.bsuir.autoservice.dao.impl.crudfactory.ICrudDaoFactory;

import java.util.HashMap;
import java.util.Map;

public class CrudDaoFactory implements ICrudDaoFactory {

    public CrudDaoFactory(){
        crudDaoMap = new HashMap<>();
    }

    @Override
    public <PrimaryKey, Entity extends Bean> void addCrud(String tableName, ICrudDao<PrimaryKey, Entity> crudDao) throws DaoException {
        try{
            assert !crudDaoMap.containsKey(tableName) : String.format("'%s' contains in crudFactory, but added", tableName);
            crudDaoMap.put(tableName, crudDao);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public void removeCrud(String tableName) throws DaoException {
        try{
            assert crudDaoMap.containsKey(tableName) : String.format("'%s' don't contains in crudFactory, but removed", tableName);
            crudDaoMap.remove(tableName);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public <PrimaryKey, Entity extends Bean> ICrudDao<PrimaryKey, Entity> getCrud(String tableName) throws DaoException {
        try{
            assert crudDaoMap.containsKey(tableName) : String.format("'%s' don't contains in crudFactory, but getted", tableName);
            return crudDaoMap.get(tableName);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    private final Map<String, ICrudDao> crudDaoMap;
}
