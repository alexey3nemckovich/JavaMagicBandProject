package main.com.bsuir.autoservice.dao.crud;

import main.com.bsuir.autoservice.dao.IDao;
import main.com.bsuir.autoservice.dao.exception.DaoException;

import java.util.List;

public interface IDaoCrud<Entity, PrimaryKey> extends IDao{
    List<Entity> getRange(int startRange, int count) throws DaoException;
    boolean update(Entity entity) throws DaoException;
    boolean delete(Entity entity) throws DaoException;
    boolean insert(Entity entity) throws DaoException;
    Entity getByPrimaryKey(PrimaryKey key) throws DaoException;
}
