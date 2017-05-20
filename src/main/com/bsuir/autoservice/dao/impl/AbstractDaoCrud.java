package main.com.bsuir.autoservice.dao.crud;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.bean.exception.BeanException;
import main.com.bsuir.autoservice.dao.database.IDatabase;
import main.com.bsuir.autoservice.dao.exception.DaoException;
import main.com.bsuir.autoservice.dao.sql.ISql;
import main.com.bsuir.autoservice.library.function.CheckedSupplier;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public abstract class AbstractDaoCrud<PrimaryKey, Entity extends Bean> implements IDaoCrud<PrimaryKey, Entity> {

    protected abstract List<Entity> parseResultSet(ResultSet rs) throws DaoException;

    protected AbstractDaoCrud(IDatabase db, ISql sql) {
        this.db = db;
        this.sql = sql;
    }

    @Override
    public int getCountRecords() throws DaoException {
        final String varName = "rowcount";
        try (PreparedStatement ps = db.getPrepareStatement(
                sql.getSelectCountQuery(getFullTableName(), varName)
        )) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(varName);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Entity> read(Map<String, String> conditions) throws DaoException{
        try (PreparedStatement ps = db.getPrepareStatement(
                    sql.getSelectWhereStatement(getFullTableName(), conditions)
            )){
            try (ResultSet rs = ps.executeQuery()) {
                return parseResultSet(rs);
            }
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public List<Entity> read(int startIndex, int count) throws DaoException {
        try (PreparedStatement ps = db.getPrepareStatement(
                    sql.getSelectRangeQuery(getFullTableName(), startIndex, count))){
            try (ResultSet rs = ps.executeQuery()) {
                return parseResultSet(rs);
            }
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(Entity entity, Map<String, String> conditionValues) throws DaoException {
        try (PreparedStatement ps = db.getPrepareStatement(
                sql.getUpdateQuery(getFullTableName(), conditionValues, entity.getFieldValuesStrings()))){
            return completeOperationDisablingFkChecks(ps::execute);
        }catch (Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Entity entity) throws DaoException {
        try (PreparedStatement ps = db.getPrepareStatement(
                sql.getDeleteQuery(getFullTableName(), entity.getFieldValuesStrings())
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
                sql.getInsertQuery(getFullTableName(), getFillQueryValues(entity)))) {

            return completeOperationDisablingFkChecks(ps::execute);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    private <T> T completeOperationDisablingFkChecks(CheckedSupplier<T, SQLException> operation) throws SQLException {
        setFkChecks(false);
        try{
            return operation.get();
        }finally {
            setFkChecks(true);
        }
    }

    @Override
    public String getFullTableName(){
        return db.getName() + "." + getTableName();
    }

    private void setFkChecks(boolean enable) throws SQLException {
        try (Statement statement = db.createStatement()) {
            int mode = enable ? 1 : 0;
            statement.execute(sql.getSetForeignKeyChecksStatement(mode));
        }
    }

    private final IDatabase db;
    private final ISql sql;
}
