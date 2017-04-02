package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.dao.impl.crud.IDaoCrud;
import main.com.bsuir.autoservice.service.IServiceCrud;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public abstract class AbstractServiceCrud<PrimaryKey,Entity> implements IServiceCrud<PrimaryKey,Entity> {
    private final IDaoCrud<Entity, PrimaryKey> daoCrud;

    protected AbstractServiceCrud(IDaoCrud daoCrud){
        this.daoCrud = daoCrud;
    }

    @Override
    public List<Entity> read(int startRange, int count) throws ServiceException {
        try {
            return daoCrud.getRange(startRange, count);
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
    public int update(List<Entity> updageEntities) throws ServiceException {
        try {
            return daoCrud.update(updageEntities);
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
