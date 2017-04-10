package main.com.bsuir.autoservice.dao.crud;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

//TODO: call with prepare statement set in components
public abstract class AbstractDaoCrud<Entity extends Bean, PrimaryKey> implements IDaoCrud<Entity, PrimaryKey> {

    protected abstract String getPrimaryKeyName();
    protected abstract List<Entity> parseResultSet(ResultSet rs) throws DaoException;

    protected AbstractDaoCrud(IDatabase db, ISql sql){
        this.db = db;
        this.sql = sql;
    }

    @Override
    public int getCountRecords() throws DaoException{
        try(Connection connection = db.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(
                    String.format("SELECT COUNT(*) AS rowcount FROM %s", getTableName())
            )) {
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
        try(Connection connection = db.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(
                    String.format("%s LIMIT %d, %d", getSelectAllQuery(),startRange,count)
            )) {
                ResultSet rs = ps.executeQuery();
                return parseResultSet(rs);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Entity entity, Map<String, String> conditionValues) throws DaoException {
        try(Connection connection = db.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(
                    sql.getUpdateQuery(getTableName(), conditionValues, entity.getFieldValues())
            )) {
                return ps.execute();
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Entity entity) throws DaoException {
        try(Connection connection = db.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(
                    sql.getDeleteQuery(getTableName(), entity.getFieldValues())
            )) {
                return ps.execute();
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean insert(Entity entity) throws DaoException {
        try(Connection connection = db.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(
                    sql.getInsertQuery(getTableName(), entity.getFieldValues())
            )) {
                return ps.execute();
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Entity getByPrimaryKey(PrimaryKey key) throws DaoException{
        try(Connection connection = db.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(
                    String.format("%s WHERE `%s` = '%s'", getSelectAllQuery(), getPrimaryKeyName(), key.toString())
            )) {
                ResultSet rs = ps.executeQuery();
                return parseResultSet(rs).get(0);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    private String getSelectAllQuery(){
        return String.format("SELECT * FROM `%s`", getTableName());
    }

    private final IDatabase db;
    private final ISql sql;
}
