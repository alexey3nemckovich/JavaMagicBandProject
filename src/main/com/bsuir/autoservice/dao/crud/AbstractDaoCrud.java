package main.com.bsuir.autoservice.dao.crud;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;

import java.sql.*;
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
        final String varName = "rowcount";
        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    sql.getSelectCountQuery(getFullTableName(), varName)
            );
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(varName);
            rs.close();
            return count;
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public List<Entity> read(Map<String, String> conditions) throws DaoException{
        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    sql.getSelectWhereStatement(getFullTableName(), conditions)
            );
            ResultSet rs = ps.executeQuery();
            return parseResultSet(rs);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public List<Entity> read(int startIndex, int count) throws DaoException {
        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    sql.getSelectRangeQuery(getFullTableName(), startIndex, count)
            );
            ResultSet rs = ps.executeQuery();
            return parseResultSet(rs);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Entity entity, Map<String, String> conditionValues) throws DaoException {
        try {
            Connection connection = db.getConnection();
            setFkChecks(false, connection);
            PreparedStatement ps = connection.prepareStatement(
                    sql.getUpdateQuery(getFullTableName(), conditionValues, entity.getFieldValues())
            );
            boolean result = ps.execute();
            setFkChecks(true, connection);
            return result;
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Entity entity) throws DaoException {
        try {
            Connection connection = db.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    sql.getDeleteQuery(getFullTableName(), entity.getFieldValues())
            );
            return ps.execute();
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public boolean insert(Entity entity) throws DaoException {
        try {
            Connection connection = db.getConnection();
            setFkChecks(false, connection);
            PreparedStatement ps = connection.prepareStatement(
                    sql.getInsertQuery(getFullTableName(), entity.getFieldValues())
            );
            boolean result =  ps.execute();
            setFkChecks(true, connection);
            return result;
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public String getFullTableName(){
        return db.getName() + "." + getTableName();
    }

    private void setFkChecks(boolean enable, Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        int mode = enable ? 1 : 0;
        statement.execute(sql.getSetForeignKeyChecksStatement(mode));
        statement.close();
    }

    private final IDatabase db;
    private final ISql sql;
}
