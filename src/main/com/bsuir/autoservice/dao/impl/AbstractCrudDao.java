package main.com.bsuir.autoservice.dao.impl;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.database.map.IDatabaseMap;
import main.com.bsuir.autoservice.dao.database.map.beanhelper.DependencyMap;
import main.com.bsuir.autoservice.dao.database.map.beanhelper.TableMap;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.IGeneralSql;
import main.com.bsuir.autoservice.library.function.CheckedFunction;
import main.com.bsuir.autoservice.library.function.CheckedSupplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCrudDao<PrimaryKey, Entity extends Bean<PrimaryKey>> implements ICrudDao<PrimaryKey, Entity> {

    protected final TableMap tableMap;

    protected abstract List<Entity> parseResultSet(ResultSet rs) throws SQLException;

    protected AbstractCrudDao(IDatabase db, IGeneralSql sql, IDatabaseMap databaseMap) {
        this.db = db;
        this.sql = sql;

        //search interface at runtime for tableMap
        this.tableMap = databaseMap.getTableMap((Class<? extends ICrudDao>) Arrays.asList(getClass().getInterfaces())
                .stream().filter(inter -> ICrudDao.class.isAssignableFrom(inter)).findFirst().get());
    }

    @Override
    public int getCountRecords() throws DaoException {
        final String varName = "rowcount";
        return executeQuery(rs->{
            rs.next();
            return rs.getInt(varName);
        }, sql.getSelectCountQuery(getTableName(), varName));
    }

    @Override
    public List<Entity> read(Map<String, String> conditions) throws DaoException{
        return executeQuery(this::parseResultSet, sql.getSelectWhereStatement(getTableName(), conditions));
    }

    @Override
    public List<Entity> read(int startIndex, int count) throws DaoException {
        return executeQuery(this::parseResultSet, sql.getSelectRangeQuery(getTableName(), startIndex, count));
    }

    @Override
    public boolean update(Entity entity, Map<String, String> conditionValues) throws DaoException {
        try (PreparedStatement ps = db.getPrepareStatement(
                sql.getUpdateQuery(getTableName(), conditionValues, entity.getFieldValuesStrings()))){
            return completeOperationDisablingFkChecks(ps::execute);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }


    protected final boolean update(Map<String, String> newValues, Map<String,String> whereStatement) throws DaoException {
        try (PreparedStatement ps = db.getPrepareStatement(
                sql.getUpdateQuery(getTableName(), whereStatement, newValues))){
                ps.execute();
                return true;
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Entity entity) throws DaoException {
        try (PreparedStatement ps = db.getPrepareStatement(
                sql.getDeleteQuery(getTableName(), entity.getFieldValuesStrings())
        )) {
            return ps.execute();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    private Map<String, String> getFillQueryValues(Entity entity) throws BeanException {
        Map<String, Object> fields = entity.getFieldValues();
        Map<String, String> queryFieldValuesStrings = new HashMap<>();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            if (entry.getValue() == null) {
                queryFieldValuesStrings.put(entry.getKey(), "NULL");
            } else {
                queryFieldValuesStrings.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return queryFieldValuesStrings;
    }

    @Override
    public boolean insert(Entity entity) throws DaoException {
        try (PreparedStatement ps = db.getPrepareStatement(
                sql.getInsertQuery(getTableName(), getFillQueryValues(entity)))) {

            return completeOperationDisablingFkChecks(ps::execute);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public final List<DependencyMap> getDependencyMaps() throws DaoException {
        return tableMap.getDependencyMaps();
    }

    private <T> T completeOperationDisablingFkChecks(CheckedSupplier<T, SQLException> operation) throws SQLException {
        setFkChecks(false);
        try{
            return operation.get();
        }finally {
            setFkChecks(true);
        }
    }

    protected final String getTableName(){
        return tableMap.getTableName();
    }

    private void setFkChecks(boolean enable) throws SQLException {
        try (Statement statement = db.createStatement()) {
            int mode = enable ? 1 : 0;
            statement.execute(sql.getSetForeignKeyChecksStatement(mode));
        }
    }



    protected final <R> R executeQuery(CheckedFunction<ResultSet, R, SQLException> checkedFunction, String statement)
            throws DaoException {
        try (PreparedStatement ps = db.getPrepareStatement(statement)) {
            try (ResultSet rs = ps.executeQuery()) {
                return checkedFunction.apply(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected final IDatabase db;
    protected final IGeneralSql sql;
}
