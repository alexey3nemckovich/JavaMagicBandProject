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

public abstract class AbstractDaoCrud<PrimaryKey, Entity extends Bean> implements IDaoCrud<PrimaryKey, Entity> {

    protected abstract List<Entity> parseResultSet(ResultSet rs) throws DaoException;

    protected AbstractDaoCrud(IDatabase db, ISql sql){
        this.db = db;
        this.sql = sql;
    }

    @Override
    public int getCountRecords() throws DaoException{
        String varName = "rowcount";
        try(Connection connection = db.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(
                    sql.getSelectCountQuery(getTableName(), varName)
            )) {
                ResultSet rs = ps.executeQuery();
                rs.next();
                int count = rs.getInt(varName);
                rs.close();
                return count;
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Entity> read(Map<String, String> conditions) throws DaoException{
        try(Connection connection = db.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(
                    sql.getSelectWhereStatement(getTableName(), conditions)
            )) {
                ResultSet rs = ps.executeQuery();
                return parseResultSet(rs);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Entity> read(int startIndex, int count) throws DaoException {
        try(Connection connection = db.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(
                    sql.getSelectRangeQuery(getTableName(), startIndex, count)
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

    private final IDatabase db;
    private final ISql sql;
}
