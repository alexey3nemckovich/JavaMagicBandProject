package main.com.bsuir.autoservice.service.impl.serviceCrud;

import main.com.bsuir.autoservice.dao.DaoController;
import main.com.bsuir.autoservice.service.impl.serviceCrud.exception.ServiceCrudException;

import java.util.List;

public class AbstractServiceCrud<PrimaryKey,Entity> implements IServiceCrud<PrimaryKey,Entity> {
    protected DaoController<Entity, PrimaryKey> daoController;

    protected AbstractServiceCrud(DaoController daoController){
        this.daoController = daoController;
    }

    @Override
    public boolean create(List<Entity> createEntities) throws ServiceCrudException {
        try {
            return daoController.insert(createEntities);
        }catch (Exception e){
            throw new ServiceCrudException(e);
        }
    }

    @Override
    public List<Entity> read(int startRange, int count) throws ServiceCrudException {
        try {
            return daoController.getRange(startRange, count);
        }catch (Exception e){
            throw new ServiceCrudException(e);
        }
    }

    @Override
    public boolean update(List<Entity> updageEntities) throws ServiceCrudException {
        try {
            return daoController.update(updageEntities);
        }catch (Exception e){
            throw new ServiceCrudException(e);
        }
    }

    @Override
    public boolean delete(List<PrimaryKey> deleteKeys) throws ServiceCrudException {
        try {
            return daoController.delete(deleteKeys);
        }catch (Exception e){
            throw new ServiceCrudException(e);
        }
    }
}
