package main.com.bsuir.autoservice.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface DaoController<Entity, PrimaryKey> {
    List<Entity> getAll() throws DaoException;
    Entity getByPrimaryKey(PrimaryKey key) throws DaoException;
    Entity update(Entity entity) throws DaoException;
    boolean delete(PrimaryKey key) throws DaoException;
    boolean insert(Entity entity) throws DaoException;
}