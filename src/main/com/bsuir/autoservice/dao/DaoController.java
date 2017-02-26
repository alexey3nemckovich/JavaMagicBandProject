package main.com.bsuir.autoservice.dao;

import main.com.bsuir.autoservice.dao.exception.DaoException;

import java.util.List;

public interface DaoController<Entity, PrimaryKey> {
    List<Entity> getAll() throws DaoException;
    Entity getByPrimaryKey(PrimaryKey key) throws DaoException;
    Entity update(Entity entity) throws DaoException;
    boolean delete(PrimaryKey key) throws DaoException;
    boolean insert(Entity entity) throws DaoException;
}