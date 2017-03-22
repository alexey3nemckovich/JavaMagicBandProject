package main.com.bsuir.autoservice.service.impl.serviceCrud;

import main.com.bsuir.autoservice.service.IService;
import main.com.bsuir.autoservice.service.impl.serviceCrud.exception.ServiceCrudException;

import java.util.List;

public interface IServiceCrud<PrimaryKey, Entity> extends IService {
    boolean create(List<Entity> createEntities) throws ServiceCrudException;
    List<Entity> read(int startRange, int count) throws ServiceCrudException;
    boolean update(List<Entity> updageEntities) throws ServiceCrudException;
    boolean delete(List<PrimaryKey> deleteKeys) throws ServiceCrudException;
    String getTableName() throws ServiceCrudException;
}
