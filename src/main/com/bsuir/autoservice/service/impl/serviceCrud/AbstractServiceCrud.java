package main.com.bsuir.autoservice.service.impl.serviceCrud;

import main.com.bsuir.autoservice.dao.DaoController;
import main.com.bsuir.autoservice.service.exception.ServiceException;

import java.util.List;

public abstract class AbstractServiceCrud<PrimaryKey,Entity> implements IServiceCrud<PrimaryKey,Entity> {
    protected DaoController<Entity, PrimaryKey> daoController;

    protected AbstractServiceCrud(DaoController daoController){
        this.daoController = daoController;
    }

    @Override
    public boolean create(List<Entity> createEntities) throws ServiceException {
        try {
            return daoController.insert(createEntities);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Entity> read(int startRange, int count) throws ServiceException {
        try {
            return daoController.getRange(startRange, count);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(List<Entity> updageEntities) throws ServiceException {
        try {
            return daoController.update(updageEntities);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(List<PrimaryKey> deleteKeys) throws ServiceException {
        try {
            return daoController.delete(deleteKeys);
        }catch (Exception e){
            throw new ServiceException(e);
        }
    }

    @Override
    public String getTableName() throws ServiceException {
        try{
            return daoController.getTableName();
        }catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
