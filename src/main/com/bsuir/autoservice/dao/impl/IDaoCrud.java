package main.com.bsuir.autoservice.dao.crud;

import main.com.bsuir.autoservice.dao.IDao;
import main.com.bsuir.autoservice.dao.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface IDaoCrud<PrimaryKey, Entity> extends IDao{
    List<Entity> read(Map<String, String> conditions) throws DaoException;
    List<Entity> read(int startRange, int count) throws DaoException;
    boolean update(Entity entity, Map<String, String> conditionValues) throws DaoException;
    boolean delete(Entity entity) throws DaoException;
    boolean insert(Entity entity) throws DaoException;
}
