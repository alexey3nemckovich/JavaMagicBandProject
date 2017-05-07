package main.com.bsuir.autoservice.service.crud;

import main.com.bsuir.autoservice.dao.crud.IDaoCrud;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;
import java.util.Map;

public abstract class AbstractServiceCrud<PrimaryKey,Entity> implements IServiceCrud<PrimaryKey,Entity> {

    protected AbstractServiceCrud(IDaoCrud<Entity, PrimaryKey> daoCrud){
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
            return daoCrud.getRange(startIndex, count);
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

    private final IDaoCrud<Entity, PrimaryKey> daoCrud;
}
