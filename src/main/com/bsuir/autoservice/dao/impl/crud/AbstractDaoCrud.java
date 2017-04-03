package main.com.bsuir.autoservice.dao.impl.crud;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.dao.database.impl.sql.ISqlDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

//TODO: call with prepare statement set in components
public abstract class AbstractDaoCrud<Entity extends Bean, PrimaryKey> implements IDaoCrud<Entity, PrimaryKey> {
    private final ISqlDatabase sqlDatabase;

    protected AbstractDaoCrud(ISqlDatabase sqlDatabase){
        this.sqlDatabase = sqlDatabase;
    }

    protected abstract String getTableNameImpl();

    protected abstract List<Entity> parseResultSet(ResultSet rs) throws DaoException;

    protected abstract String getPrimaryKeyName();

    //protected abstract String getOrderedFields();

    private String getSelectQuery(){
        return String.format("SELECT * FROM `%s`",getTableNameImpl());
    }

    @Override
    public String getTableName() throws DaoException{
        try {
            return getTableNameImpl();
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public int getAllCount() throws DaoException{
        try(Connection connection = sqlDatabase.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(String.format("SELECT COUNT(*) AS rowcount FROM %s",
                    getTableName()))) {
                ResultSet rs = ps.executeQuery();
                rs.next();
                int count = rs.getInt("rowcount");
                rs.close();
                return count;
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Entity> getRange(int startRange, int count) throws DaoException {
        try(Connection connection = sqlDatabase.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(String.format("%s LIMIT %d, %d",
                    getSelectQuery(),startRange,count))) {
                ResultSet rs = ps.executeQuery();
                return parseResultSet(rs);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Entity getByPrimaryKey(PrimaryKey key) throws DaoException{
        try(Connection connection = sqlDatabase.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(String.format("%s WHERE `%s` = '%s'",
                    getSelectQuery(),getPrimaryKeyName(), key.toString()))) {
                ResultSet rs = ps.executeQuery();
                return parseResultSet(rs).get(0);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    private String getInsertQuery(List<Entity> insertEntities){
        throw new NotImplementedException();
        //return String.format("INSERT INTO `%s` (%s) VALUES %s",getTableNameImpl(),getOrderedFields(),getConvertedValues(insertEntities));
    }

    protected String getConvertedValues(List<Entity> insertEntities){
        StringBuilder stringBuilder = new StringBuilder();
        for (Entity insertEntity: insertEntities) {
            //TODO: complete bean get value
            throw new NotImplementedException();
            //stringBuilder.append(updateEntity.getAllFields().values());
        }
        return stringBuilder.toString();
    }

    @Override
    public int update(List<Entity> updateEntities) throws DaoException {
        try(Connection connection = sqlDatabase.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(getUpdateQuery(updateEntities))) {
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    private String getUpdateQuery(List<Entity> updateEntities){
        StringBuilder stringBuilder = new StringBuilder();
        for (Entity updateEntity: updateEntities) {
            //TODO: complete bean get value
            throw new NotImplementedException();
            //stringBuilder.append(String.format("UPDATE `service` SET `name` = '1233' WHERE `service`.`id` = 1"));
        }
        return stringBuilder.toString();
    }

    private String getDeleteQuery(List<PrimaryKey> deleteKeys) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PrimaryKey deleteKey : deleteKeys) {
            stringBuilder.append(String.format("DELETE FROM `%s` WHERE `%s`.`%s` = %s",
                    getTableNameImpl(),getTableNameImpl(), getPrimaryKeyName(), deleteKey.toString()));
        }
        return stringBuilder.toString();
    }

    @Override
    public int delete(List<PrimaryKey> deleteKeys) throws DaoException {
        try(Connection connection = sqlDatabase.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(getDeleteQuery(deleteKeys))) {
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int insert(List<Entity> insertEntities) throws DaoException {
        try(Connection connection = sqlDatabase.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(getInsertQuery(insertEntities))) {
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}