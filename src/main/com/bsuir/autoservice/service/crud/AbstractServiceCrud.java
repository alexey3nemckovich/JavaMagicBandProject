package main.com.bsuir.autoservice.service.crud;

import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.crud.IDaoCrud;
import main.com.bsuir.autoservice.service.BaseService;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;

public abstract class AbstractServiceCrud<PrimaryKey,Entity> implements IServiceCrud<PrimaryKey,Entity> {
    private final IDaoCrud<Entity, PrimaryKey> daoCrud;

    protected AbstractServiceCrud(IDaoCrud daoCrud){
        this.daoCrud = daoCrud;
    }

    @Override
    public int readTotalCount() throws ServiceException{
        try {
            return daoCrud.getAllCount();
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
    public int create(List<Entity> createEntities) throws ServiceException {
        try {
            return daoCrud.insert(createEntities);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public int update(List<Entity> updateEntities) throws ServiceException {
        try {
            return daoCrud.update(updateEntities);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public int delete(List<PrimaryKey> deleteKeys) throws ServiceException {
        try {
            return daoCrud.delete(deleteKeys);
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
}
